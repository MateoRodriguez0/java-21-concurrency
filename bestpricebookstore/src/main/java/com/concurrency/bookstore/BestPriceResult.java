package com.concurrency.bookstore;


import java.util.List;

public record BestPriceResult(/*RestCallStatistics callStatistics,*/ Book bestPriceDeal, List<Book> allDeals) {

}
