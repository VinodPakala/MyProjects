package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpResponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		url = serviceUrl + apiUrl;
		
	}
	
	@Test(priority=1, description= "get API testing without headers")
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		closeablehttpResponse = restClient.get(url);
		
		//status code
		int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status code is  :"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//json String 
		String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The JSON response is  :"+ responseJson);
		
		//single value assertion
		//per_page value validation
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("the per_page value is  :"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total value validation
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("the total_value value is  :"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from JSONArray
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//Headers
		Header[] headersArray = closeablehttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header headers : headersArray) {
			allHeaders.put(headers.getName(), headers.getValue());
		}
		System.out.println("Headers Array is  :"+ allHeaders);
		Assert.assertEquals(allHeaders.get("Transfer-Encoding"), "chunked");
		System.out.println("Actual header is same as expected - Test case PASS");
		
	}
	
	@Test(priority=2, description="get API testing without headers")
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		closeablehttpResponse = restClient.get(url);
		
		//status code
		int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status code is  :"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//json String 
		String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The JSON response is  :"+ responseJson);
		
		//single value assertion
		//per_page value validation
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("the per_page value is  :"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total value validation
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("the total_value value is  :"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from JSONArray
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//Headers
		Header[] headersArray = closeablehttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header headers : headersArray) {
			allHeaders.put(headers.getName(), headers.getValue());
		}
		System.out.println("Headers Array is  :"+ allHeaders);
		
	}
	
	

}
