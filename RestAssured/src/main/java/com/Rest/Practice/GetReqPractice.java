package com.Rest.Practice;

import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetReqPractice {

	@Test(description = "validation of md5 param value as test")
	public void md5_ParamTest() {
		
		String queryParamValue = "test";
		String expectedParamValue = "test";
		
		Response res = given().param("text", queryParamValue).
				when().get("http://md5.jsontest.com");
		
		String actualmd5 = res.getBody().path("original");
		System.out.println("actual value of md5 is  :"+ actualmd5);
		assertEquals(res.getStatusCode(), 200);
		System.out.println("response status code is  :"+ res.getStatusCode());
		assertEquals(expectedParamValue, actualmd5);
		System.out.println("actual and expected are same - Test case pass ");
		
	}
	
//	@Test(description = "Validation of circuit count for year_2017")
//	public void validateCircuitCount_2017() {
//		
//		int expectedCircuitCount = 20;
//		String season= "2017";
//		
//		Response resp = given().pathParam("raceSeason", season).
//				        when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
//		
//		int actualCircuitCount = resp.getBody().path("MRData.total");
//		assertEquals(actualCircuitCount, expectedCircuitCount);
//		System.out.println("actual and expected are same test case _ pass");
//		
//		int actCircuitCount = resp.jsonPath().getList("MRData.CircuitTable.Circuits.circuitId").size();
//		assertEquals(actCircuitCount, expectedCircuitCount);
//		System.out.println("actual and expected are same test case _ pass");
//	}
	
	
	@Test
	public void IteratingOverHeaders()
	{
//	 Response response = 
//			 given().header("Accept","application/json").
//	         when().get("http://restapi.demoqa.com/utilities/weather/city/Pune");
		
		Response response = 
				 given().auth().basic("Test", "Test123").
		         when().get("http://restapi.demoqa.com/utilities/weather/city/Pune");
	 
	 //validate response body is correct
	// System.out.println(response.getBody().asString());
	// System.out.println(response.headers());
	 //saving all the headers inside allHeaders so that we can fetch it later
	 assertEquals(response.getStatusCode(),200);
	 System.out.println(" ************** Header as input in GET method is working as Expected**********");
	 Headers allHeaders = response.headers();
//	 
	 // Iterate over all the Headers using advanced for loop
	 for(Header header : allHeaders)
 {
		 System.out.println(header.getName() + header.getValue());
	// use if condition to check for specific Key value pair
	 if (header.getName().equalsIgnoreCase("X-Proxy-Cache")) 
	 {
		 
		 //assert the value with expected
		 assertEquals(header.getValue(),"MISS");
		 System.out.println("The value for key Server is : " + header.getValue());
	 }
  }
}
	
}
