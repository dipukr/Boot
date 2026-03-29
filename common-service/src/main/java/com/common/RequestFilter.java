package com.common;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestFilter implements Filter {
	
	@Autowired
	private LoggingService loggingService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		var response = HttpServletResponse.class.cast(resp);
		var request = HttpServletRequest.class.cast(req);
		var requestURI = request.getRequestURI();
		
		if (requestURI.startsWith("/actuator") || requestURI.equals("/health")) {
			chain.doFilter(request, response);
			return;
		}
		var reqWrapper = new ContentCachingRequestWrapper(request, 1024 * 1024);
		var resWrapper = new ContentCachingResponseWrapper(response);

		var start = Instant.now();
		chain.doFilter(reqWrapper, resWrapper);
		long duration = Duration.between(start, Instant.now()).toMillis();

		loggingService.logRequest(reqWrapper);
		loggingService.logResponse(reqWrapper, resWrapper, duration);

		resWrapper.copyBodyToResponse();
	}
}
