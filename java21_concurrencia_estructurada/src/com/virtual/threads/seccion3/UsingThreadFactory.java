package com.virtual.threads.seccion3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
@SuppressWarnings("preview")
public class UsingThreadFactory {

	
	public static void main(String[] args) throws InterruptedException {
		
		executorUsingThreadFactory();
	}

	public static void executorUsingThreadFactory() {
		
		ThreadFactory factory=Thread.ofVirtual().name("userthread",0).factory();
		
		try(ExecutorService service=Executors.newThreadPerTaskExecutor(factory)){
			service.submit(VirtualThreadsCreation::handleUserRequest);
			service.submit(VirtualThreadsCreation::handleUserRequest);
			service.submit(VirtualThreadsCreation::handleUserRequest);
			service.submit(VirtualThreadsCreation::handleUserRequest);
			service.submit(VirtualThreadsCreation::handleUserRequest);
		}
		
	}
	
	public static void executorUsingThreadFactory2() throws InterruptedException {
		ThreadFactory factory=Thread.ofVirtual().name("userthread",0).factory();
		
		Thread v1= factory.newThread(VirtualThreadsCreation::handleUserRequest);
		Thread v2= factory.newThread(VirtualThreadsCreation::handleUserRequest);
		
		v1.start();
		v2.start();
		
		v1.join();
		v2.join();
	}
	
}
