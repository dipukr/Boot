package com.common;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoggingService {
	private static final ObjectMapper mapper = CustomObjectMapperHolder.getMapper();
	
	public void logRequest(ContentCachingRequestWrapper request) {
		byte[] content = request.getContentAsByteArray();
		var body = new String(content, StandardCharsets.UTF_8);
		var data = new StringBuilder();
		data.append("REQUEST ");
		data.append("method=[").append(request.getMethod()).append("] ");
		data.append("path=[").append(request.getRequestURI()).append("] ");
		data.append("headers=[").append(headers(request)).append("] ");
		data.append("params=[").append(params(request)).append("] ");
		data.append("body=[").append(body).append("] ");
		log.info(data.toString());
	}
	
	public void logRequest(HttpServletRequest request, Object body) {
		var data = new StringBuilder();
		data.append("REQUEST ");
		data.append("method=[").append(request.getMethod()).append("] ");
		data.append("path=[").append(request.getRequestURI()).append("] ");
		data.append("headers=[").append(headers(request)).append("] ");
		data.append("params=[").append(params(request)).append("] ");
		data.append("body=[");
		try {
			data.append(mapper.writeValueAsString(body)).append("] ");
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		log.info(data.toString());
	}
	
	public void logResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, long duration) {
		byte[] content = response.getContentAsByteArray();
		var body = new String(content, StandardCharsets.UTF_8);
		var data = new StringBuilder();
		data.append("RESPONSE ");
		data.append("method=[").append(request.getMethod()).append("] ");
		data.append("path=[").append(request.getRequestURI()).append("] ");
		data.append("headers=[").append(headers(response)).append("] ");
		data.append("body=[").append(body).append("] ");
		log.info(data.toString());
	}
	
	public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
		var data = new StringBuilder();
		data.append("REQUEST ");
		data.append("method=[").append(request.getMethod()).append("] ");
		data.append("path=[").append(request.getRequestURI()).append("] ");
		data.append("headers=[").append(headers(request)).append("] ");
		data.append("params=[").append(params(request)).append("] ");
		data.append("body=[");
		try {
			data.append(mapper.writeValueAsString(body)).append("] ");
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		log.info(data.toString());
	}
	
	public HashMap<String, String> params(HttpServletRequest request) {
		var params = new HashMap<String, String>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			String val = request.getParameter(key);
			params.put(key, val);
		}
		return params;
	}
	
	public HashMap<String, String> headers(HttpServletRequest request) {
		var headers = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String val = request.getHeader(key);
			headers.put(key, val);
		}
		return headers;
	}
	
	public Map<String, String> headers(HttpServletResponse response) {
		return response.getHeaderNames().stream()
			.collect(Collectors.toMap(header -> header, response::getHeader));
	}
}
