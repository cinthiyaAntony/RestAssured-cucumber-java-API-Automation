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

public class BatchSD {
	Response response;	
	
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


	@When("^Get request to (.*)$")
	public void get_request_to(String URI) {
		
		response = given().when().get(URI);
	}

	@Then("^Validate response code (.*)$")
	public void Validate_response_code(Integer statusCode) {

	 response.then().assertThat().statusCode(statusCode).log().all();
	 
	}
	
	@Then("^Validate batch Id is displayed as (.*)$")
	public void Validate_batch_Id_is_displayed(Integer batchId) {

	 response.then().body("batchId",equalTo(batchId));
	 
	}
	
	@Then("^Validate Program Id is displayed as (.*)$")
	public void Validate_Program_Id_is_displayed(Integer programId) {

	 response.then().body("programId",hasItems(programId));
	 
	}
	
	@Then("^Validate batch Name is displayed as (.*)$")
	public void Validate_batch_Name_is_displayed(String batchName) {

	 response.then().body("batchName",hasItems(batchName));
	 
	}


	@When("^post request to (.*)$")
	public void post_request_to(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName","Jan23-NinjaSpark-SDET-001");
		request.put("batchDescription","Test");
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",10);
		request.put("programId",1447);

		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post(URI);
	}

	@When("^put request to (.*)$")
	public void put_request_to_batches_batchId(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName","Jan23-NinjaSpark-SDET-003");
		request.put("batchDescription","Java");
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",10);
		request.put("programId",1447);
		request.put("programId","Program-2023042034474");


		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().put(URI);

	}

	@When("^Delete request to (.*)$")
	public void delete_request_to(String URI) {	

		response = when().
				delete(URI);
	}
}
