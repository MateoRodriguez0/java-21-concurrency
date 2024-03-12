package com.structured.concurrency.doc;

import java.nio.file.AccessDeniedException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;

public class Basico {

	public static void main(String[] args) /*throws InterruptedException*/ {
		
		System.out.println("inicio el main");
		Callable<String> hola= () -> {
			
			throw new AccessDeniedException("token invalido");
			//TimeUnit.SECONDS.sleep(2);
			//return "Hola";
			
		};
	
		Callable<String> mundo= () -> {
			throw new AccessDeniedException("token invalido");
			//TimeUnit.SECONDS.sleep(2);
			//return "Mundo";
		};
		
		try (var task = new StructuredTaskScope<String>()) {
			Subtask<String> subtask1= task.fork(hola);
			Subtask<String> subtask2= task.fork(mundo);
			
			try {
				task.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			if(subtask1.state()!=State.FAILED&& subtask1.state()!=State.FAILED) {
				System.out.println(subtask1.get()+subtask2.get());
			}
	
			
		}
		
		/*try (var task = new StructuredTaskScope.ShutdownOnFailure()) {
			Subtask<String> subtask1= task.fork(hola);
			Subtask<String> subtask2= task.fork(mundo);
			
			task.join();
			try {
				task.throwIfFailed();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(subtask1.get()+subtask2.get());
		}
		*/
		/*try (var task = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
		Subtask<String> subtask1= task.fork(hola);
		Subtask<String> subtask2= task.fork(mundo);
		
		task.join();
	
		String result;
		try {
			result = task.result();
			System.out.println(result);
		} catch (ExecutionException e) {

		}
		
	
	}*/
		
		System.out.println("Finalizo el main");
	}
}
