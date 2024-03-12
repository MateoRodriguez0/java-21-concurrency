package com.mudra;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class UserRequestHandler implements Callable<String> {

	@Override
	public String call() throws Exception {
		try(ExecutorService service= Executors.newVirtualThreadPerTaskExecutor();){
			
			CompletableFuture<String> cf1=CompletableFuture.supplyAsync(this::dbCall1, service)
					.thenCombine(CompletableFuture.supplyAsync(this::dbCall2,service), (db1, db2) -> db1+" "+ db2);
			
			CompletableFuture<String> cf2=cf1.thenCombine(CompletableFuture.supplyAsync(this::restCall1, service)
					.thenCombine(CompletableFuture.supplyAsync(this::restCall2, service), (rest1, rest2) ->  rest1+" "+ rest2), 
					
					(db, rest) ->db+" "+ rest );
			
			return cf2.get();
			
		}
		
		
	}

	@SuppressWarnings("unused")
	private String copletablesFutures(ExecutorService service) throws InterruptedException, ExecutionException {
		Long long1=System.currentTimeMillis();
		CompletableFuture< String> cf1=CompletableFuture
				.supplyAsync(this::dbCall, service);
		
		CompletableFuture<String> cf2=cf1
				.thenCombineAsync(CompletableFuture.supplyAsync(this::restCall, service), (t, u) -> t.concat(u));
		
		String result=cf2.get();
		Long long2=System.currentTimeMillis();
		System.out.println(long2-long1);
		System.out.println(result);
		
		return result;
	}


	@SuppressWarnings("unused")
	private String futuros(ExecutorService service) throws InterruptedException, ExecutionException {
		Long long1=System.currentTimeMillis();
		Future<String> f1=service.submit(this::restCall);
		Future<String> f2=service.submit(this::dbCall);
		String result=f1.get().concat(" ").concat(f2.get());
		Long long2=System.currentTimeMillis();
		System.out.println(long2-long1);
		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unused")
	private String llamadassecuenciales() {
		//secuencial
		Long long1=System.currentTimeMillis();
		String result1=dbCall();
		String result2=restCall();
		String result=result1.concat(" ").concat(result2);
		Long long2=System.currentTimeMillis();
		System.out.println(long2-long1);
		System.out.println(result);
		
		return result;
	}
	@SuppressWarnings("unused")
	private String llamadasAsincronas() {
		//secuencial
		Long long1=System.currentTimeMillis();
		String result1=dbCall();
		String result2=restCall();
		String result=result1.concat(" ").concat(result2);
		Long long2=System.currentTimeMillis();
		System.out.println(long2-long1);
		System.out.println(result);
		
		return result;
	}

	
	private String dbCall() {
		NetworkCaller net= new  NetworkCaller("data");
		try {
			return net.makeCall(5);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	@SuppressWarnings("unused")
	private String restCall() {
		NetworkCaller net= new  NetworkCaller("rest");
		try {
			return net.makeCall(2);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
	
		}
	
	}
	
	@SuppressWarnings("unused")
	private String dbCall1() {
		NetworkCaller net= new  NetworkCaller("data");
		try {
			return net.makeCall(2);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	@SuppressWarnings("unused")
	private String restCall1() {
		NetworkCaller net= new  NetworkCaller("rest");
		try {
			return net.makeCall(4);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
	
		}
	
	}
	private String dbCall2() {
		NetworkCaller net= new  NetworkCaller("data");
		try {
			return net.makeCall(3);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	@SuppressWarnings("unused")
	private String restCall2() {
		NetworkCaller net= new  NetworkCaller("rest");
		try {
			return net.makeCall(5);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
	
		}
	
	}
}
