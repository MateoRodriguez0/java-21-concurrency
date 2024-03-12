package com.virtual.threads.seccion3;

import java.lang.Thread.Builder.OfVirtual;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("preview")
public class VirtualThreadsCreation {

	
	public static void main(String[] args) throws InterruptedException {
		//usando el virtual thread builder
		
		OfVirtual virtualT= Thread.ofVirtual().name("userthread",0);
		
		Thread v1= virtualT.start(VirtualThreadsCreation::handleUserRequest);
		Thread v2= virtualT.start(VirtualThreadsCreation::handleUserRequest);
		
		v1.join();
		v2.join();
	}
	
	public static void handleUserRequest() {
		System.out.println("inicio de manejo de ususarios- hilo "+Thread.currentThread().threadId());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finalizacion de manejo de ususarios- hilo "+Thread.currentThread().threadId());
		
	}
}
