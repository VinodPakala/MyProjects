package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.UsersData;

public class PostAPITest extends TestBase {
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	public void SetUp() {
		
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		url = serviceUrl  + apiUrl;
		
	}
	
	
	@Test
	public void postRequestValidation() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API
		ObjectMapper mapper = new ObjectMapper();
		UsersData users = new UsersData("Micheal", "leader"); // expected user details
		
		// object to json file - Marshelling
		mapper.writeValue(new File("/Users/indunadam/eclipse-workspace/APIproject/src/main/java/com/qa/data/users.json"), users);
		
		//object to json String
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.post(url, userJsonString, headerMap); // call the API
		
		//validate response
		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
		
		//jsonString
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	    
		JSONObject jsonObject = new JSONObject(responseString);
		System.out.println("the response API is " + jsonObject  );
		
		// json to POJO
		UsersData userRespObj = mapper.readValue(responseString, UsersData.class); // actual user details
		System.out.println(userRespObj);
		
		// 
		Assert.assertTrue(users.getName().equals(userRespObj.getName()));
		Assert.assertTrue(users.getJob().equals(userRespObj.getJob()));
		
	}
	
	

}
									