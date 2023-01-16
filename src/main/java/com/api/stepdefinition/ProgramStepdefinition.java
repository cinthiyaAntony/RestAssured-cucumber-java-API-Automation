package com.api.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.module.Program;
import com.api.utils.ResponseHandler;
import com.api.utils.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;

public class ProgramStepdefinition {
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(ProgramStepdefinition.class);

	public ProgramStepdefinition(TestContext context) {
		this.context = context;
	}

	@Given("user has access to endpoint {string}")
	public void user_has_access_to_endpoint(String endpoint) {
		given().contentType(ContentType.JSON);
		// context.session.put("endpoint", endpoint);

	}

	@When("user makes a request to view all program")
	public void user_makes_a_request_to_view_all_program() {
		context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
		ResponseBody body = context.response.getBody(); // .jsonPath().getString("program");
		System.out.println("Response Body is: " + body.asString());
		LOG.info("Program ID: " + body);
//		assertNotNull("Program ID not found!", Program);
//		context.session.put("programID", Program);
	}

	@Then("user should get the response code {int}")
	public void user_should_get_the_response_code(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
	}

	@Then("user should see all the program details")
	public void user_should_see_all_the_program_details() {
		Program[] programIDs = ResponseHandler.deserializedResponse(context.response, Program[].class);
		assertNotNull("Program ID not found!!", programIDs);

	}

	@When("user makes a request to view all program IDs")
	public void user_makes_a_request_to_view_all_program_i_ds() {

	}

	@When("user generate")
	public void user_generate(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Then("user should get the POST response code {int}")
	public void user_should_get_the_post_response_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("user validates the response with JSON schema {string}")
	public void user_validates_the_response_with_json_schema(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("user makes a request to view program IDs")
	public void user_makes_a_request_to_view_program_i_ds() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("user makes a request to delete program with basic auth {string} & {string}")
	public void user_makes_a_request_to_delete_program_with_basic_auth(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("user makes a request to view Program Name")
	public void user_makes_a_request_to_view_program_name() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("user makes a request to delete booking with basic auth {string} & {string}")
	public void user_makes_a_request_to_delete_booking_with_basic_auth(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
