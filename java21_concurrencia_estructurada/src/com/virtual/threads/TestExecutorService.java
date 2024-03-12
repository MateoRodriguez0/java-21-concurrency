package com.virtual.threads;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("preview")
public class TestExecutorService {

	/*
	 * Incertidumbre, por que los hilos virtuales no se pueden dormir
	 * Respuesta, si se pueden dormir, pero los hilos son de demonio 
	 * y los hilos de demonio muern cuando los hilos de usuarios finalizan y la jvm se apaga
	 */
	public static void main(String[] args) throws InterruptedException {
		LocalDateTime dateTime1= LocalDateTime.now();
		ExecutorService ex= Executors.newVirtualThreadPerTaskExecutor();
		for(int i=100; i>0; i--) {
			ex.submit(() ->{
				System.out.println("virtual thread "+h );
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				System.out.println("virtual thread finish");
			});
	
		}
		
		ex.awaitTermination(1, TimeUnit.SECONDS);
		ex.close();
		LocalDateTime dateTime2= LocalDateTime.now();
		long segundos = ChronoUnit.SECONDS.between(dateTime1, dateTime2);
		System.out.println(dateTime1);
		System.out.println(dateTime2);
		System.out.println("las tareas duraron "+segundos+ " segundos");
	}
	
	static int h=0;
}
