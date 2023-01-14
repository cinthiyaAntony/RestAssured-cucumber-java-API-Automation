package stepDefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RestUtil;

public class BatchSD {
	Response response;
	static Integer batchID;
	static String batchNm;
	static Integer programID;
	static Integer NewprogramID;
	static String programName;
	String batchname = RestUtil.batchName();
	String batchDescription = RestUtil.batchDescription();
	String batchNoOfClasses = RestUtil.batchNoOfClasses();
	String NewprogramName = RestUtil.programName();
	String programDescription = RestUtil.programDescription();
	String dateAndTime = RestUtil.getDateTime();
	
	
	@Given("A Service with LMS API")
	public void a_Service_with_lms_API() {
		
		Properties prop;

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("Configuration/config.properties");
				prop.load(fis);
				String baseURL = prop.getProperty("baseurl");
				baseURI = baseURL;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
		}		 
	}
	
	@When("Get request to {string}")
	public void get_request_to(String URI) {
		
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
		
		String path = URI +programID ;
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
	
	@When("post request for program to {string}")
	public void post_request_for_program_to(String URI) {

		JSONObject request = new JSONObject();

		request.put("programName",NewprogramName);
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
		NewprogramID = response.path("programId");
		System.out.println(dateAndTime);
	}



	@When("post request to {string}")
	public void post_request_to(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName",batchname);
		request.put("batchDescription",batchDescription);
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",10);
		request.put("programId",NewprogramID);

		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post(URI);
		
		batchID = response.path("batchId");
		batchNm = response.path("batchName");
		programID = response.path("programId");
	}
	
	
	@When("put request to {string}")
	public void put_request_to_batches_batchId(String URI) {
		
		String path = URI +batchID ;
		JSONObject request = new JSONObject();

		request.put("batchName",batchname);
		request.put("batchDescription",batchDescription);
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",batchNoOfClasses);
		request.put("programId",NewprogramID);
		request.put("programId",programName);


		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().put(path);

	}

	@When("Delete request to {string}")
	public void delete_request_to(String URI) {	
		
		String path = URI +batchID ;
		response = when().
				delete(path);
	}
}