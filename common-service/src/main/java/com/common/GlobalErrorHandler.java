package com.common;

import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(BusinessException.class)
	public Rest<Void> handleNotFound(BusinessException ex) {
		return Rest.error(ex.getMessage(), 500);
	}

	@ExceptionHandler(Exception.class)
	public Rest<Void> handleException(Exception ex) {
		return Rest.error(ex.getMessage(), 500);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public Rest<Void> handleConstraint(ConstraintViolationException ex) {
		String message = ex.getConstraintViolations().stream().map(v -> v.getMessage()).findFirst()
				.orElse("Validation error");
		return Rest.error(message, 400);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public Rest<Void> handleValidation(HandlerMethodValidationException ex) {
		String message = ex.getParameterValidationResults().stream()
				.flatMap(result -> result.getResolvableErrors().stream()).map(error -> error.getDefaultMessage())
				.findFirst().orElse("Validation error");
		return Rest.error(message, 400);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Rest<Void> handleValidation(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.joining(", "));
		return Rest.error(errorMessage, 400);
	}
}
