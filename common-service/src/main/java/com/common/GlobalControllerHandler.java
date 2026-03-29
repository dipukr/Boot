package com.common;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GlobalControllerHandler implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getMethod().equals(HttpMethod.TRACE.name())) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return false;
		}
		return true;
	}
}
