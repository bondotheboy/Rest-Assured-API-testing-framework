package postAPITests;

import java.io.IOException;

import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utilities;

public class BatchValidation extends Utilities {

	RequestSpecification request;
	Response response;
	SoftAssert softAssert;

	@BeforeClass(description = "Setup API request")
	public void setup() {
		RestAssured.baseURI = returnURI("URI");
		request = RestAssured.given();
	}

	@Test(description = "Run batch request")
	public void TC01_batch_request() throws IOException {
		loadData("BATCH_DATA");
		request.header("Content-Type", "application/json");
		request.body(batchRequest.toString());
		response = request.post("/validateBatch");
		JSONArray actualResponse = new JSONArray(response.asString());
		validate(actualResponse);
	}

	public void validate(JSONArray actualResponse) {
		softAssert = new SoftAssert();
		int statusCode = response.getStatusCode();
		softAssert.assertEquals(statusCode, 200);
		if (actualResponse.length() == batchResponse.length()) {
			for (int n = 0; n < actualResponse.length(); n++) {
				String actualStatus = actualResponse.getJSONObject(n).getString("status").trim();
				String actualMessage = "null";
				if (actualResponse.getJSONObject(n).get("messages") != null)
					actualMessage = actualResponse.getJSONObject(n).get("messages").toString().trim();
				String expectedStatus = batchResponse.getJSONObject(n).get("Expected status").toString().trim();
				String expectedMessage = batchResponse.getJSONObject(n).getString("Expected messages").toString()
						.replace("%c%", ",").trim();
				softAssert.assertEquals(actualStatus, expectedStatus,
						"Status mismatch ovserved for TC --> " + testCaseIdentifier.get(n));
				softAssert.assertTrue(actualMessage.contains(expectedMessage),
						"Message mismatch observed for TC --> " + testCaseIdentifier.get(n));

			}
			softAssert.assertAll();
		}

	}

}
