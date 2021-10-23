package com.qa.definitions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Validate_AddNewCustomer {

	private RequestSpecification req;
	private Response resp;

	@Given("User sets the base-url as {string}")
	public void user_sets_the_base_url_as(String baseurl) {
		RestAssured.baseURI = baseurl;
		req = RestAssured.given().contentType(ContentType.JSON);
	}

	@Given("User sets username as {string} and password as {string} for Basic Authentication")
	public void user_sets_username_as_and_password_as_for_basic_authentication(String username, String password) {
		req = req.auth().basic(username, password);
	}

	@When("sets below data as the payload")
	public void sets_below_data_as_the_payload(DataTable dataTable) {
		List<List<String>> data = dataTable.asLists();
		Map<String,String> payload = new LinkedHashMap<>();	
		for(List<String> rows : data) {
			payload.put(rows.get(0), rows.get(1));
		}

		JSONObject jsonPayload = new JSONObject() {{ payload.forEach((k,v) -> put(k.toString(), v)); }};	  
        req = req.body(jsonPayload.toString());
	}

	@When("User sets service as {string} and sends POST Request to the endpoint")
	public void user_sets_service_as_and_sends_post_request_to_the_endpoint(String service) {
		resp = req.request(Method.POST, service);
	}

	@Then("User verifies if Status code is {int}")
	public void user_verifies_if_response_code_is(Integer respCode) {
		System.out.println("Response Body: " + resp.getBody().asPrettyString());
		Assert.assertEquals((int) respCode, resp.getStatusCode());
	}

}
