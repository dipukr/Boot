package com.common;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RequestBodyHandler extends RequestBodyAdviceAdapter {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private LoggingService loggingService;

	@Override
	public boolean supports(MethodParameter methodParam, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		
		return false;
	}
}
