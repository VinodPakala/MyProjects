
package com.Rest.Practice;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetRequest {

//	@Test(description="Validate md5 with query parameter as test")
//	public void Validate_md5() {
//	    
//	    String queryParamValue = "test";//TestData fetched from Postman or given by requirement documents
//	    String expectedMd5Value = "test";//Fetch from manual test case or JIRA 
//	    
//	    //given(),when(),then()
//	    //query,path,header,cookies---all will  with given()
//	    //API methods like GET/PUT/POST/DELETE will be defined under when()
//	    //Validation of the response can be done using then()
//	    
//	   Response resp = given().param("text",queryParamValue).
//			   when().get("http://md5.jsontest.com");
//	   
//	   
//	   String actual = resp.getBody().path("original");//Fetch the value of key md5
//	   System.out.println("Actual value of md5 is: " + actual);
//	   assertEquals(resp.getStatusCode(),200);
//	   System.out.println("The Status code is : 200");
//       assertEquals(expectedMd5Value,actual);//compare the output from API response with the expected value
//	   System.out.println("The Actual value is same as Expected- Test Case Pass");
//	   
//	//   System.out.println("+++++++++++"+ resp.asString() + "+++++++++++++++++++++");
//	   
////	    then().
////	        assertThat().
////	        body("md5",equalTo(expectedMd5CheckSum));
//	}
	
	@Test (description="TC_001-Validate number of circuit ID for the year 2017")
	public void ValidateCountCircuitID_2017() {
	        
	   // String season = "2017";
	    int ExpectedCountCircuit_ID = 20; //Expected value
	        
      Response resp = given().
	        	pathParam("raceSeason","2017").
	        when().
	        	get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
	   
	  String n =  resp.getBody().path("MRData.total");
	  System.out.println("Actual count of circuit ID is: " + n);
	  //count number of time circuit id is present in the circuits list
	  
	  int num = resp.jsonPath().getList("MRData.CircuitTable.Circuits.circuitId").size();
	//  int number = resp.getBody().path("MRData.CircuitTable.Circuits.circuitId").
      System.out.println("Number of times circuit ID is present in the Ciucuits list: "+ num);
      assertEquals(num,ExpectedCountCircuit_ID);
	}

}
