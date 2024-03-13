package com.concurrency.bookstore;

import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/virtualstore")
public class BestPriceBookController {

	@GetMapping("/book")
	public BestPriceResult getBestPriceForBook(@RequestParam String name) {
		try {
			List<Book> books = bookService.getBookFromAllStores(name);
			Book BestPrice=books
					.stream()
					.sorted(Comparator.comparing(Book::cost))
					.findFirst().orElseThrow();
			
			return new BestPriceResult(BestPrice, books);
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	
	}
	
	@Autowired
	private BookRetrievalService bookService;
}
