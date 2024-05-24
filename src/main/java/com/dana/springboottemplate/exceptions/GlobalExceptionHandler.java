package com.dana.springboottemplate.exceptions;

import com.dana.springboottemplate.dto.BaseResponse;
import com.dana.springboottemplate.dto.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<BaseResponse<Object>> handleErrorValidation(ValidationException exception) {
		BaseResponse<Object> response = BaseResponse.builder(HttpStatus.BAD_REQUEST, exception.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
