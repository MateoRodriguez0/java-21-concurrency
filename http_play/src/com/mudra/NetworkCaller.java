package com.mudra;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class NetworkCaller {

	private String callname;

	public NetworkCaller(String callname) {
		super();
		this.callname = callname;
	}

	public String makeCall(int sec) throws IOException, InterruptedException {
		System.out.println(callname+" : BEG call: "+ Thread.currentThread());
		
		URI uri= URI.create("https://httpbin.org/delay/"+sec);
		HttpClient client= HttpClient.newBuilder().build();
		HttpRequest request=HttpRequest.newBuilder(uri).build();
		HttpResponse<String> response =client.send(request, BodyHandlers.ofString());
		
		System.out.println(callname+" : END call: "+ Thread.currentThread());
		return response.body();
	}
}
