package com.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private GlobalControllerHandler controllerHandler;

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(controllerHandler).addPathPatterns("/**")
			.excludePathPatterns("/actuator/**", "/health", "/favicon.ico");
	}
}
