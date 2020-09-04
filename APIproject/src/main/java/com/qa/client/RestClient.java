package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//GET Method without Headers
		public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);  //http get request
			CloseableHttpResponse closeablehttpResponse = httpClient.execute(httpget);  // hit the GET

			return closeablehttpResponse;
			
		}

		
	//GET Method with Headers
		public CloseableHttpResponse get(HashMap<String, String> headerMap, String url) throws ClientProtocolException, IOException {
					
			CloseableHttpClient httpClient = HttpClients.createDefault(); //to create Http Client
			HttpGet httpget = new HttpGet(url);  //http get request
					
			for(Map.Entry<String, String> entity : headerMap.entrySet()) {
				httpget.addHeader(entity.getKey(), entity.getValue());
				}
			CloseableHttpResponse closeablehttpResponse = httpClient.execute(httpget);  // hit the GET
            return closeablehttpResponse;
					
		}

		//POST Method
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url); //http post request
			httpPost.setEntity(new StringEntity(entityString)); // for payload
			
			//for Headers
			for(Map.Entry<String, String> entity : headerMap.entrySet()) {
				
				httpPost.addHeader(entity.getKey(), entity.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
			return closeableHttpResponse;
		}
		
		
}
