package com.mudra;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class HttpPlayer {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ThreadFactory factory= Thread.ofPlatform().name("request-handler ",0).factory();
		
		try(ExecutorService service= Executors.newThreadPerTaskExecutor(factory);){
			IntStream.range(0, Num_Users).forEach(value ->{
				service.submit(new UserRequestHandler());
			});
			
		}
	}
	
	
	private static final int Num_Users=1;
}
