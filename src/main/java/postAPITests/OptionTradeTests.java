package postAPITests;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utilities;

public class OptionTradeTests extends Utilities {
	
	RequestSpecification request;
	Response response;
	SoftAssert softAssert;

	@BeforeTest(description = "Setup API request")
	public void setup() {
		RestAssured.baseURI = returnURI("URI");
		request = RestAssured.given();
	}

	@Test(description = "OPTIONS:For American style, exerciseStartDate is after trade date but before the expiry date")
	public void tc17_options_american_exerciseStartDate_after_tradeDate_before_expiryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, exerciseStartDate before trade date & expiry date should throw error")
	public void tc18_options_american_excerciseStartDate_before_tradeDate_and_expiryDate() throws IOException{
		
		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, exerciseStartDate after trade date & expiry date should throw error")
	public void tc19_options_american_excerciseStartDate_after_tradeDate_and_expiryDate() throws IOException{
		
		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, deliveryDate before premiumDate should throw error")
	public void tc20_options_american_deliveryDate_before_premiumDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, expiryDate on weekend should throw error")
	public void tc21_options_american_expiryDate_on_weekend() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, expiryDate before tradeDate should throw error")
	public void tc22_options_american_expiryDate_before_tradeDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, expiryDate after deliveryDate should throw error")
	public void tc23_options_american_expiryDate_after_deliveryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, missing exerciseStartDate should throw error")
	public void tc24_options_american_missing_excerciseStartDate() throws IOException{
		
		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For American style, invalid tradeDate should throw error")
	public void tc25_options_american_invalid_tradeDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	
	@Test(description = "OPTIONS:For European style, expiryDate & premiumDate should be before deliveryDate")
	public void tc26_options_european_expiryDate_premiumDate_before_deliveryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For invalid style, API should throw error")
	public void tc27_options_invalid_style() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For European style, when premiumDate & deliveryDate is same, API should throw error")
	public void tc28_options_european_premiumDate_same_as_deliveryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For European style, expiryDate is after premiumDate but before deliveryDate")
	public void tc29_options_european_expiryDate_after_premiumDate_before_deliveryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();

	}
	
	@Test(description = "OPTIONS:For European style, expiryDate & premiumDate is after deliveryDate")
	public void tc30_options_european_expiryDate_premiumDate_after_deliveryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "OPTIONS:For European style, invalid deliveryDate should throw error")
	public void tc31_options_european_invalid_deliveryDate() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	public void validate(){
		softAssert = new SoftAssert();
		int statusCode = response.getStatusCode();
		softAssert.assertEquals(statusCode, 200);
		String actualStatus = response.jsonPath().get("status");
		String actualMessage = "null";
		if(response.jsonPath().get("messages")!=null)
		 actualMessage = response.jsonPath().get("messages").toString();
		softAssert.assertEquals(actualStatus,expectedOutput.get("Expected status"),"Expected and actual post response status doesn't match");
		softAssert.assertTrue(actualMessage.contains(expectedOutput.get("Expected messages").replace("%c%", ",")),"Expected and actual Post response messages doesn't match");
		softAssert.assertAll();
	}
	
}
