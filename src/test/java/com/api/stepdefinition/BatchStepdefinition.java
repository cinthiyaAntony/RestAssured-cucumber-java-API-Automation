package com.api.stepdefinition;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;

import com.api.base.TestBase;
import com.api.utils.RestUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BatchStepdefinition extends TestBase{


	Response response;
	static Integer batchID;
	static String batchNm;
	static String batchDescrtn;
	static Integer programID;
	static Integer batchNoClass;
	static String programName;
	String batchname = RestUtil.batchName();
	String batchDescription = RestUtil.batchDescription();
	String batchNoOfClasses = RestUtil.batchNoOfClasses();
	String NewprogramNm = RestUtil.programName();
	String programDescription = RestUtil.programDescription();
	String dateAndTime = RestUtil.getDateTime();
	String baseURL = prop.getProperty("baseurl");

	@Given("A Service with LMS API")
	public void a_Service_with_lms_API() {
		
		baseURI = baseURL;
			
	}

	@When("Get request to view all batches {string}")
	public void get_request_to_view_all_batches(String URI) {

		response = given().when().get(URI);
	}


	@When("Get request by batch ID to {string}")
	public void get_request_by_batch_ID_to(String URI) {

		String path = URI +batchID ;
		response = given().when().get(path);
	}

	@When("Get request by batch name to {string}")
	public void get_request_by_batch_name_to(String URI) {

		String path = URI +batchNm ;
		response = given().when().get(path);
	}

	@When("Get request by program ID to {string}")
	public void get_request_by_program_ID_to(String URI) {

		String path = URI + programID;
		response = given().when().get(path);
	}


	@Then("Validate response code {int}")
	public void Validate_response_code(Integer statusCode) {

		response.then().assertThat().statusCode(statusCode).log().all();

	}

	@Then("Validate batch Id is displayed as {int}")
	public void Validate_batch_Id_is_displayed(Integer batchId) {

		batchId = batchID;
		response.then().body("batchId",equalTo(batchId));

	}

	@Then("Validate Program Id is displayed as {int}")
	public void Validate_Program_Id_is_displayed(Integer programId) {


		programId = programID;
		response.then().body("programId",hasItems(programId));

	}

	@Then("Validate batch Name is displayed as {string}")
	public void Validate_batch_Name_is_displayed(String batchName) {

		batchName=batchNm;
		response.then().body("batchName",hasItems(batchName));

	}

	@When("post request to create program {string}")
	public void post_request_to_create_program(String URI) {

		JSONObject request = new JSONObject();

		request.put("programName",NewprogramNm);
		request.put("programDescription",programDescription);
		request.put("programStatus","Active");
		request.put("creationTime",dateAndTime);
		request.put("lastModTime",dateAndTime);

		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post(URI);

		programName = response.path("programName");
		programID = response.path("programId");

	}



	@When("post request to create batch {string}")
	public void post_request_to_create_batch(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName",batchname);
		request.put("batchDescription",batchDescription);
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",batchNoOfClasses);
		request.put("programId",programID);

		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post(URI);

		batchID = response.path("batchId");
		batchNm = response.path("batchName");
		batchDescrtn = response.path("batchDescription");
		batchNoClass = response.path("batchNoOfClasses");
	}
	
	@When("post request to create duplicate batch {string}")
	public void post_request_to_create_duplicate_batch(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName",batchNm);
		request.put("batchDescription",batchDescrtn);
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",batchNoClass);
		request.put("programId",programID);

		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post(URI);

	}


	@When("put request to update batch data {string}")
	public void put_request_to_update_batche_data(String URI) {


		JSONObject request = new JSONObject();

		request.put("batchName",batchname);
		request.put("batchDescription",batchDescription);
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",batchNoOfClasses);
		request.put("programId",programID);
		request.put("programId",programName);

		String path = URI +batchID ;
		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().put(path);

	}

	@When("Delete batch request to {string}")
	public void delete_batch_request_to(String URI) {	

		String path = URI +batchID ;
		response = when().
				delete(path);
	}
	
	@When("Delete already deleted batch request to {string}")
	public void delete_already_deleted_batch_request_to(String URI) {	

		String path = URI +batchID ;
		response = when().
				delete(path);
	}

	@When("Delete program request to {string}")
	public void delete_program_request_to(String URI) {	

		String path = URI +programName ;
		response = when().
				delete(path);
	}


}