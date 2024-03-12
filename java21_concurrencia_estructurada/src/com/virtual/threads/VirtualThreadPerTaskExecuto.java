package com.virtual.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
@SuppressWarnings("preview")
public class VirtualThreadPerTaskExecuto {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		var executor= Executors.newVirtualThreadPerTaskExecutor();
		Future<String> future1= executor.submit(() ->{
		
			System.out.println("so");
			return "Ejecutando thread";	
		});

		future1.get();
		executor.close();
	    System.out.println("Terminando ejecucion");
	    
		   
	    
	
		
	}
}
