package com.windowsazure.messaging;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.HttpHost;

public class HttpClientManager {
	private static CloseableHttpAsyncClient httpAsyncClient;
	
	public static CloseableHttpAsyncClient getHttpAsyncClient(String proxyHost, int proxyPort) {
		if(httpAsyncClient == null) {
			synchronized(HttpClientManager.class) {
				if(httpAsyncClient == null) {
					CloseableHttpAsyncClient client = null;
					if (proxyHost != null) {
						// set proxy
						HttpHost proxy = new HttpHost(proxyHost, proxyPort);
						client = HttpAsyncClients.custom().setProxy(proxy).build();
					} else {
						client = HttpAsyncClients.createDefault();
					}
					client.start();
					httpAsyncClient = client;
				}
			}
		}
		  
		return httpAsyncClient;
	}
		
	public static void setHttpAsyncClient(CloseableHttpAsyncClient httpAsyncClient) {
		synchronized(HttpClientManager.class) {
			if(httpAsyncClient == null) {
				HttpClientManager.httpAsyncClient = httpAsyncClient;
			}
			else{
				throw new RuntimeException("HttpAsyncClient was already set before or default one is being used.");
			}
		}
	}
}
