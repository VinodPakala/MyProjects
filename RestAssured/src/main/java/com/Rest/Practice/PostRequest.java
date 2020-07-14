package com.Rest.Practice;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostRequest {

	@Test( priority = 1, enabled = true, description ="Validaion of POST request using JSONObject")
	public void postRequestValidation() {
		
	   JSONObject jsonObject = new JSONObject();
	   jsonObject.put("FirstName", "virender");
	   jsonObject.put( "LastName", "sign");
	   jsonObject.put( "UserName", "virender12");
	   jsonObject.put( "Password", "virender@1234");
	   jsonObject.put( "Email", "v.sign@gmail.com");
       
	   Response resp = 
			   given().header("Content-Type", "application/json").body(jsonObject.toString()).
			   when().post("http://restapi.demoqa.com/customer/register");
	   
	   assertEquals(resp.getStatusCode(),200);
	   String responseString = resp.getBody().asString();
	    System.out.println(responseString);
	    System.out.println("=====================================================================");
	   
	}
	
	@Test( priority = 2,enabled = true, description = "Validation of POST request usin JSON File")
	public void validatePostRequestWithJsonFile() throws IOException {
		
		FileInputStream jsonFile = new FileInputStream(new File(System.getProperty("user.dir" )+ "/TestData/testData.json" ));
		Response resp = 
				given().header("Content-Type", "application/json").body(IOUtils.toString(jsonFile)).
				when().post("http://restapi.demoqa.com/customer/register");
		
		assertEquals(resp.getStatusCode(),200);
		System.out.println(resp.getBody().asString());
	}

	
}
