package com.virtual.threads;
@SuppressWarnings("preview")
public class ThreadAndThreadBuilder {

	public static void main(String[] args) throws InterruptedException {
		Thread.Builder builder=Thread.ofVirtual().name("Worker- ", 0);
		Runnable task= () -> {
			System.out.println("Thread id: "+ Thread.currentThread().threadId());
		};
		
		//name worker 0
		Thread t1= builder.start(task);
		t1.join();
		System.out.println(t1.getName() + " terminated");
		
		// name "worker-1"
		Thread t2 = builder.start(task);   
		t2.join();  
		System.out.println(t2.getName() + " terminated");

	}
}
