package stepDefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BatchSD {

	final String baseURL = "https://lms-backend-service.herokuapp.com/lms";
	Response response;


	@Given("^Get call to (.*)$")
	public void get_call_to(String URI) {

		RestAssured.baseURI = baseURL;
		response = given().when().get(URI);

	}

	@Then("^Response is (.*)$")
	public void response_is(Integer statusCode) {

		Object actualStatusCode = response.then().log().all().extract().statusCode();		
		Assert.assertEquals(statusCode,actualStatusCode);

	}



	@Given("^post request to (.*)$")
	public void post_request_to(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName","Jan23-NinjaSpark-SDET-001");
		request.put("batchDescription","Test");
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",10);
		request.put("programId",1447);


		RestAssured.baseURI = baseURL;
		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post(URI);
	}

	@Given("^put request to (.*)$")
	public void put_request_to_batches_batchId(String URI) {

		JSONObject request = new JSONObject();

		request.put("batchName","Jan23-NinjaSpark-SDET-001");
		request.put("batchDescription","Java");
		request.put("batchStatus","Active");
		request.put("batchNoOfClasses",10);
		request.put("programId",1447);
		request.put("programId","Program-2023042034474");

		RestAssured.baseURI = baseURL;
		response = given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(request.toJSONString()).
				when().put(URI);

	}

	@Given("^Delete request to (.*)$")
	public void delete_request_to(String URI) {

		RestAssured.baseURI = baseURL;

		response = when().
				delete(URI);
	}



}
