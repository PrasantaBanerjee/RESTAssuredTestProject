package com.qa.definitions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Validate_UpdateCustomerDetails {

	private RequestSpecification req;
	private Response resp;

	@Given("User has set base-url as {string}")
	public void user_has_set_base_url_as(String baseurl) {
		RestAssured.baseURI = baseurl;
		req = RestAssured.given().contentType(ContentType.JSON);
	}

	@Given("User has set the username as {string} and password as {string} for Basic Authentication")
	public void user_has_set_the_username_as_and_password_as_for_basic_authentication(String username, String password) {
		req = req.auth().basic(username, password);
	}

	@Given("sends below data as the payload")
	public void sends_below_data_as_the_payload(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> data = dataTable.asLists();
		Map<String, String> json = new LinkedHashMap<>();
		for (List<String> rows : data) {
			json.put(rows.get(0), rows.get(1));
		}

		JSONObject payload = new JSONObject() {
			{
				json.forEach((k, v) -> put(k.toString(), v));
			}
		};
		req = req.body(payload.toString());
	}

	@When("User sets the service as {string} and sends PUT Request for Customer ID {string}")
	public void user_sets_the_service_as_and_sends_put_request_for_customer_id(String service, String custID) {
		resp = req.request(Method.PUT, service + "/" + custID);
	}

	@Then("User will verify if the Status code is {int}")
	public void user_will_verify_if_the_status_code_is(Integer respCode) {
		System.out.println("Response Body: " + resp.getBody().asPrettyString());
		Assert.assertEquals((int) respCode, resp.getStatusCode());
	}

}
