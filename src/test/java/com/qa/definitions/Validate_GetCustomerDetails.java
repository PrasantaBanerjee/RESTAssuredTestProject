package com.qa.definitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Validate_GetCustomerDetails {

	private RequestSpecification req;
	private Response resp;

	@Given("User sets base-url as {string}")
	public void user_sets_base_url_as(String baseurl) {
		RestAssured.baseURI = baseurl;
		req = RestAssured.given().contentType(ContentType.JSON);
	}

	@Given("User sets the username as {string} and password as {string} for Basic Authentication")
	public void user_sets_the_username_as_and_password_as_for_basic_authentication(String username, String password) {
		req = req.auth().basic(username, password);
	}

	@When("User sets the service as {string} and sends GET Request to the endpoint")
	public void user_sets_the_service_as_and_sends_get_request_to_the_endpoint(String service) {
		resp = req.request(Method.GET, service);
	}

	@Then("User then verifies if Status code is {int}")
	public void user_then_verifies_if_response_code_is(Integer respCode) {
		Assert.assertEquals((int) respCode, resp.getStatusCode());
	}

	@Then("prints the opening message in console")
	public void prints_the_opening_message_in_console() {
		System.out.println("Response Body: \n" + resp.getBody().asPrettyString());
	}

}
