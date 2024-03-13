package com.concurrency.loom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping(value = "/")
	public String getThreadInfo() {
		
		return Thread.currentThread().toString();
	}
}
