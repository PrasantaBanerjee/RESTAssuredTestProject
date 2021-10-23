package com.qa.definitions;

import org.assertj.core.api.SoftAssertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Validate_DeleteCustomer {

	private RequestSpecification req;
	private Response resp;

	@Given("User has set the base-url as {string}")
	public void user_has_set_the_base_url_as(String baseUrl) {
		RestAssured.baseURI = baseUrl;
		req = RestAssured.given().contentType(ContentType.JSON);
	}

	@Given("User has set username as {string} and password as {string} for Basic Authentication")
	public void user_has_set_username_as_and_password_as_for_basic_authentication(String username, String password) {
		req = req.auth().basic(username, password);
	}

	@When("User sets service as {string} and sends DELETE Request for Customer ID {string}")
	public void user_sets_service_as_and_sends_delete_request_for_customer_id(String service, String custID) {
		resp = req.request(Method.DELETE, service + "/" + custID);
	}

	@Then("User verifies if the Status code is {int}")
	public void user_verifies_if_the_status_code_is(Integer statusCode) {
		SoftAssertions assertion = new SoftAssertions();
		assertion.assertThat(resp.getStatusCode()).isEqualTo(statusCode);
		assertion.assertAll();
	}

}
