package com.common;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommonController {
	
	@PostMapping("/test")
	public Rest<List<String>> handlerMethod(@RequestBody RequestBO requestBO) {
		return Rest.ok(requestBO.getGender().stream()
				.map(Enum::name)
				.toList(), "Fetched successfully");
	}
}
