package com.api.StepDefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.junit.Assert;

import com.api.base.TestBase;
import com.api.utils.ExcelReader;
import com.api.utils.RestUtil;
import com.google.gson.JsonObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramStepDefinition extends TestBase {

	String baseURL = prop.getProperty("baseurl");
	// String configExcel = prop.getProperty("excel");
	String uri;
	static String NewprogramName;
	static int NewprogramID;
	public RequestSpecification request;
	int status;
	JsonObject jsonObject;
	String currentTime = RestUtil.getDateTime();
	String lastModTime = RestUtil.getDateTime();
	String progNa = RestUtil.programNameForExcel();
	String progDes = RestUtil.programDescriptionForExcel();
	Response response;
	String jsonAsString;

	// GET ALL THE USERS
	@Given("User sets request with authorization")
	public void user_sets_request_with_authorization() {
		// String allUser = "/allPrograms";
		this.uri = baseURL;
		this.request = RestAssured.given() // .auth().preemptive().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
				.header("Content-Type", "application/json");
	}

	@When("User sends GET request {string}")
	public void user_sends_GET_request(String URI) { // allPrograms //allProgram
		this.uri = baseURL + URI;
		response = this.request.get(this.uri);
		response.then().log().all();
	}

	@Then("User should get status code {string}, valid response body and json schema validated for successful GET request")
	public void user_should_get_status_code_valid_response_body_and_json_schema_validated_for_successful_GET_request(
			final String statuscode) {

		final int StatusCodeUser = response.getStatusCode();
		if (StatusCodeUser == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			System.out.println("Successful Status Code" + StatusCodeUser);
			jsonAsString = response.asString();
			response.then().assertThat()
					.body(JsonSchemaValidator
							.matchesJsonSchema(new File("src/test/resources/JsonSchemaUser/allprogram.json")))
					.log().all();
			Assert.assertEquals(StatusCodeUser, 200);
			System.out.println("scenario 1 is sucess");

		} else {
			System.out.println("Not Found: " + StatusCodeUser);
			Assert.assertEquals(StatusCodeUser, 404);
		}
	}

	// CREATE Program Postive Scenario
	@SuppressWarnings("unchecked")
	@When("User sends POST request with data")
	public void user_sends_POST_request_with_data() {
		String createUser = "/saveprogram";
		this.uri = baseURL + createUser;

		final JSONObject body = new JSONObject();
		body.put("programName", progNa);
		body.put("programDescription", progDes);
		body.put("programStatus", "Active");
		body.put("creationTime", currentTime);
		body.put("lastModTime", lastModTime);
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
		NewprogramName = response.path("programName");
		NewprogramID = response.path("programId");
		System.out.println(NewprogramID);
		System.out.println(NewprogramName);

	}

	@Then("User should get status code {string}")
	public void user_should_get_status_code(String statusCode) {
		final int StatusCodeUser = response.getStatusCode();
		if (StatusCodeUser == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("created : " + StatusCodeUser);
			Assert.assertEquals(StatusCodeUser, 201);
		} else {
			System.out.println("Bad Request: " + StatusCodeUser);
			Assert.assertTrue(false);
		}
	}

	// CREATE Program For Negative Scenario
	@SuppressWarnings("unchecked")
	@When("User sends POST request with data using {string} and {int}")
	public void user_sends_POST_request_with_data_using_and(final String SheetName, final Integer Rownumber)
			throws InvalidFormatException, IOException {
		String createUser = "/saveprogram";
		this.uri = baseURL + createUser;
		final ExcelReader excelReader = new ExcelReader();
		final List<Map<String, String>> postData = excelReader.getData(RestUtil.EXCEL, SheetName);
		final String name = postData.get(Rownumber).get("programName");
		final String description = postData.get(Rownumber).get("programDescription");
		final String status = postData.get(Rownumber).get("programStatus");
		final JSONObject body = new JSONObject();
		body.put("programName", name);
		body.put("programDescription", description);
		body.put("programStatus", status);
		body.put("creationTime", currentTime);
		body.put("lastModTime", lastModTime);
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("User should get status code {string} for duplicate entry")
	public void user_should_get_status_code_for_duplicate_entry(final String statuscode) {

		final int StatusCodeUser = response.getStatusCode();
		if (StatusCodeUser == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			System.out.println("Successful Status Code: " + StatusCodeUser);
			Assert.assertEquals(statuscode, "400");
			System.out.println("Duplicate Entry: " + StatusCodeUser);
			Assert.assertTrue(true);
		} else {
			System.out.println("Not Successful" + StatusCodeUser);
			Assert.assertTrue(false);
		}
	}

	// GET PROGRAM By ID ViewSingleProgramId
	@When("User sends GET request for single Programid data")
	public void user_sends_GET_request_for_single_Programid_data() throws InvalidFormatException, IOException {
		String endPoint = "/programs/";
		uri = baseURL + endPoint;
		String path = uri + NewprogramID;
		System.out.println(path);
		response = given().when().get(path).then().log().all() // .body(body.toJSONString())
				.extract().response();
	}

	// GETProgramName
	@When("User sends GET request for single ProgramName data")
	public void user_sends_GET_request_for_single_ProgramName_data() throws InvalidFormatException, IOException {
		String endPoint = "/programs/";
		uri = baseURL + endPoint;
		String path = uri + NewprogramName;
		response = given().when().get(path).then().log().all() // .body(body.toJSONString())
				.extract().response();

	}

	@Then("User should get status code {string} for getProgramId and Name")
	public void user_should_get_status_code_for_getProgramId_and_Name(String statusCode) {
		final int StatusCodeUser = response.getStatusCode();
		if (StatusCodeUser == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Successful Status Code: " + StatusCodeUser);
//			response.then().assertThat().body(JsonSchemaValidator
//					.matchesJsonSchema(new File("src/test/resources/JsonSchemaUser/allprogram.json")));
			Assert.assertEquals(StatusCodeUser, 200);
		} else {
			System.out.println("Response received successfully: " + StatusCodeUser);
			Assert.assertEquals(StatusCodeUser, 400);
		}
	}

	// UpdateProgramId
	@SuppressWarnings("unchecked")
	@When("User sends put request to {string} and {string} and {int}")
	public void user_sends_put_request_to_and_and(String URI, final String SheetName, final Integer Rownumber)
			throws InvalidFormatException, IOException {

		this.uri = baseURL + URI + NewprogramID;
		final ExcelReader excelReader = new ExcelReader();
		final List<Map<String, String>> putData = excelReader.getData(RestUtil.EXCEL, SheetName);

		final String name = putData.get(Rownumber).get("programName");
		final String description = putData.get(Rownumber).get("programDescription");
		final String status = putData.get(Rownumber).get("programStatus");
		JSONObject body = new JSONObject();
		body.put("programName", name);
		body.put("programDescription", description);
		body.put("programStatus", status);
		body.put("creationTime", currentTime);
		body.put("lastModTime", lastModTime);
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(body.toJSONString()).when()
				.put(this.uri);
	}

	@Then("User should get status code update validation {string}")
	public void user_should_get_status_code_update_validation(String statusCode) {
		final int StatusCodeUser = response.getStatusCode();
		if (StatusCodeUser == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Successful Status Code: " + StatusCodeUser);
//			response.then().assertThat().body(JsonSchemaValidator
//					.matchesJsonSchema(new File("src/test/resources/JsonSchemaUser/allprogram.json")));
			Assert.assertEquals(StatusCodeUser, 200);
		} else {
			System.out.println("Response received successfully: " + StatusCodeUser);
			Assert.assertEquals(StatusCodeUser, 400);
		}
	}

//DeleteById
	@When("User sends Delete program by id {string}")
	public void user_sends_Delete_program_by_id(String URI) {
		this.uri = baseURL + URI + NewprogramID;
		response = when().delete(this.uri).then().extract().response();
	}

	// DeleteByName
	@When("User sends Delete program by Name {string}")
	public void user_sends_Delete_program_by_Name(String URI) {
		this.uri = baseURL + URI + NewprogramName;
		response = when().delete(this.uri).then().extract().response();
	}

	@Then("User should validate status code {string}")
	public void user_should_validate_status_code_deletebyprogname(String statusCode) {
		final int StatusCodeUser = response.getStatusCode();
		if (StatusCodeUser == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("created : " + StatusCodeUser);
			Assert.assertEquals(StatusCodeUser, 201);
		} else if (StatusCodeUser == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Successful Status Code: " + StatusCodeUser);
			Assert.assertEquals(StatusCodeUser, 200);
		} else {
			System.out.println("Bad Request: " + StatusCodeUser);
			Assert.assertTrue(false);

		}
	}

}