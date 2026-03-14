package com.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rest<T> {
	private String message;
	private boolean success;
	private int status;
	private T data;

	public static <T> Rest<T> ok(T data, String message, int status) {
		var response = new Rest<T>();
		response.data = data;
		response.status = status;
		response.message = message;
		response.success = true;
		return response;
	}
	
	public static <T> Rest<T> ok(T data, String message) {
		var response = new Rest<T>();
		response.data = data;
		response.status = 200;
		response.message = message;
		response.success = true;
		return response;
	}

	public static <T> Rest<T> error(String message, int status) {
		var response = new Rest<T>();
		response.status = status;
		response.message = message;
		response.success = false;
		return response;
	}
}
