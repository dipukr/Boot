package com.common;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

	@Autowired
	private LoggingService loggingService;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParam,
			MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		if (request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
			loggingService.logResponse(ServletServerHttpRequest.class.cast(request).getServletRequest(),
					ServletServerHttpResponse.class.cast(response).getServletResponse(), body);
		}
		return body;
	}
}
