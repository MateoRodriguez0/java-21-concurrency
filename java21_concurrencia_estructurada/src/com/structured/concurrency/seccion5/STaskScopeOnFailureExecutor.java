package com.structured.concurrency.seccion5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.structured.concurrency.seccion5.LongRunningTask.TaskResponse;

public class STaskScopeOnFailureExecutor  {
	
	public static Map<String, TaskResponse> execute(List<LongRunningTask> runningTasks) 
			throws InterruptedException, ExecutionException{	
		
		List<Subtask<TaskResponse>> Subtasks=new ArrayList<>();
		try(var scope=new StructuredTaskScope.ShutdownOnFailure()){
			runningTasks.forEach(l -> {
				Subtasks.add(scope.fork(l));
			});
			
			scope.join();
			scope.throwIfFailed();
			
	
			return Subtasks.stream()
					.filter(sub ->sub.state()==State.SUCCESS )
					.map(sub ->sub.get())
					.collect(Collectors.toMap(TaskResponse::name,Function.identity()));
		}
		
	}
}
