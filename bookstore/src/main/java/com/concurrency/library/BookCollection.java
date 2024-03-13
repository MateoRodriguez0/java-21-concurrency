package com.concurrency.library;

import com.concurrency.library.model.Book;

public interface BookCollection {

	Book findBook(String name);
}
