package postAPITests;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utilities;

public class SpotTradeTests extends Utilities {
	RequestSpecification request;
	Response response;
	SoftAssert softAssert;

	@BeforeClass(description = "Setup API request")
	public void setup() {
		RestAssured.baseURI = returnURI("URI");
		request = RestAssured.given();
	}
	

	@Test(description = "SPOT:Value date is after trade date, with valid counterparty, legal entity and currency")
	public void tc01_spot_value_date_after_trade_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Value date is same as trade date")
	public void tc02_spot_value_date_same_as_trade_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Value date is before trade date")
	public void tc03_spot_value_date_before_trade_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Check supported counterparty")
	public void tc04_spot_check_supported_counterparty() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();

	}

	@Test(description = "SPOT:Unsupported counterparty should throw an error")
	public void tc05_spot_unsupported_counterparty() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Using an illegal entity should throw an error")
	public void tc06_spot_illegal_entity() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Invalid currencies should throw an error")
	public void tc07_spot_invalid_currency() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Value date on weekends should throw an error")
	public void tc08_spot_weekend_value_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "SPOT:Value date on currency non-working day should throw an error")
	public void tc09_spot_value_date_currency_non_working_day() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}
	
	@Test(description = "SPOT:Invalid Value date should throw an error")
	public void tc010_spot_invalid_value_date() throws IOException {

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
