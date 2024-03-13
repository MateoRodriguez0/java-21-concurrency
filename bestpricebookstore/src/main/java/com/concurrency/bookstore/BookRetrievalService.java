package com.concurrency.bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BookRetrievalService {
	
	public List<Book> getBookFromAllStores (String name) throws InterruptedException{
		List<Subtask<Book>> subtasks= new ArrayList<>();
		  
    	ThreadFactory factory = Thread.ofVirtual().name("book-store-thr",0).factory();
        try (var scope = new StructuredTaskScope<Book>("virtualstore", factory)) {
			endpoints.forEach((servicio, url) -> {
				subtasks.add(scope.fork(() -> getBookFromStore(servicio, url, name)));
			});
			scope.join();
		}
		
		subtasks.stream()
          .filter(t -> t.state() == State.FAILED)
          .map(Subtask::exception)
          .forEach(e -> e.printStackTrace());
		  
		return subtasks.stream()
				.filter(sub ->sub.state()==State.SUCCESS)
				.map(Subtask::get)
				.collect(Collectors.toList());
	}
	
    private Book getBookFromStore(String storeName, String url, String bookName) {
        Book book = restClient.get()
                .uri(url + "/store/book", t -> t.queryParam("name", bookName).build())
                .retrieve()
                .body(Book.class);
        
        return book;
    }
    

	@Value("#{${book.store.baseurls}}")
	private Map<String, String> endpoints;
	
	private RestClient restClient= RestClient.create();
}
