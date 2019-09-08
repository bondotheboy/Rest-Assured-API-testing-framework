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

public class ForwardTradeTests extends Utilities {

	RequestSpecification request;
	Response response;
	SoftAssert softAssert;

	@BeforeTest(description = "Setup API request")
	public void setup() {
		RestAssured.baseURI = returnURI("URI");
		request = RestAssured.given();
	}


	@Test(description = "FORWARD:Value date is after trade date, with valid counterparty, legal entity and currency")
	public void tc11_forward_value_date_after_trade_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "FORWARD:Value date is same as trade date")
	public void tc12_forward_value_date_same_as_trade_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "FORWARD:Value date is before trade date")
	public void tc13_forward_value_date_before_trade_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "FORWARD:Value date on weekends should throw an error")
	public void tc14_forward_weekend_value_date() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "FORWARD:Value date on currency non-working day should throw an error")
	public void tc15_forward_value_date_currency_non_working_day() throws IOException {

		loadData(new Throwable().getStackTrace()[0].getMethodName());
		JSONObject postBody = new JSONObject(testCaseData);
		request.header("Content-Type", "application/json");
		request.body(postBody.toString());
		response = request.post("/validate");
		validate();
	}

	@Test(description = "FORWARD:Invalid value date should throw an error")
	public void tc16_invalid_value_date() throws IOException {

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
