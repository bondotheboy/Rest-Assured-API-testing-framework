package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONArray;

public class Utilities {
	
	public HashMap<String, String> testCaseData;
	public HashMap<String, String> expectedOutput;
	public JSONArray batchRequest;
	public JSONArray batchResponse;
	public ArrayList<String> testCaseIdentifier;
	InputStream input = null;

	public String returnURI(String URI) {
		return readProperties(URI);
	}
	
	public String readProperties(String field) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(new File(System.getProperty("user.dir").toString() + "\\service.properties"));
			prop.load(input);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(field);
	}

	public void loadData(String testCaseName) throws IOException {

		testCaseData = new HashMap<String, String>();
		expectedOutput = new HashMap<String, String>();
		batchRequest = new JSONArray();
		batchResponse = new JSONArray();
		testCaseIdentifier = new ArrayList<String>();
		File testCaseSpecificCSV = new File(System.getProperty("user.dir") + "\\TestData.csv");
		BufferedReader tcSpecificCSV = new BufferedReader(new FileReader(testCaseSpecificCSV));
		readCSV(testCaseName, tcSpecificCSV);
	}

	private void readCSV(String testCaseName, BufferedReader br) throws IOException {
		String st;
		boolean testCaseFound = false;
		while ((st = br.readLine()) != null) {
			if (st.startsWith(testCaseName)) {
				loadValues(st);
				testCaseFound = true;
				break;
			}
			if(testCaseName.equals("BATCH_DATA")){
				createBatchRequestResponseBody(st);
				testCaseFound = true;
			}
		}
		if(!testCaseFound){
			throw new InvalidParameterException("Value for test case --> "+testCaseName+" <--not found in test data sheet");
		}
	}

	private void createBatchRequestResponseBody(String st) {
		expectedOutput = new HashMap<String, String>();
		testCaseData = new HashMap<String, String>();
		testCaseIdentifier.add(loadValues(st));
		batchRequest.put(testCaseData);
		batchResponse.put(expectedOutput);
	}

	public String loadValues(String st) {
		for (int i = 1; i < st.split(",").length; i++) {
			if(!(st.split(",")[i].split("=")[0].equals("Expected status")||st.split(",")[i].split("=")[0].equals("Expected messages")))
				testCaseData.put(st.split(",")[i].split("=")[0], st.split(",")[i].split("=")[1].trim());
			else
				expectedOutput.put(st.split(",")[i].split("=")[0], st.split(",")[i].split("=")[1].trim());
				
		}
		return st.split(",")[0];
	}


}
