package com.structured.concurrency.doc;

import java.util.concurrent.StructuredTaskScope;

public class CustomStructureTask <T> extends StructuredTaskScope<T>{

	@Override
	protected void handleComplete(Subtask<? extends T> subtask) {
	
		super.handleComplete(subtask);
	}
	
	@Override
	public StructuredTaskScope<T> join() throws InterruptedException {
		// TODO Auto-generated method stub
		return super.join();
	}
	
	
	
	
}
