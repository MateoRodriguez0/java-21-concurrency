package com.virtual.threads;

import java.lang.Thread.Builder;
@SuppressWarnings("preview")
public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		//Thread		
		//Thread thread= Thread.ofVirtual().start(() -> System.out.println("Hello world"));
		//thread.join();
		
		//Thread.builder
		
		Runnable tarea= () -> {
			Thread thread= Thread.ofVirtual().start(() -> System.out.println("Hello world"));
			try {
				thread.join();
				System.out.println("Hello world Thread Builder");
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		};
		 
		Thread.Builder threadBuilder= (Builder) Thread.ofVirtual();
		Thread thread2=threadBuilder.start(tarea);
		thread2.join();
	}
}
