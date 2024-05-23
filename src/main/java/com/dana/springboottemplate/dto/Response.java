package com.dana.springboottemplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

	private Boolean status;

	private String code;

	private String message;

	private String readableMessage;

	private T data;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> errors;

	@JsonInclude
	private Pagination pagination;

	public static <T> Response<T> ok() {
		return ok(null);
	}

	public static <T> Response<T> ok(T data) {
		return status(true, HttpStatus.OK, data, null);
	}

	public static <T> Response<T> badRequest(List<String> errors) {
		return badRequest(errors, null);
	}

	public static <T> Response<T> badRequest(List<String> errors, T data) {
		return status(false, HttpStatus.BAD_REQUEST, data, errors);
	}

	public static <T> Response<T> badRequest(List<String> errors, String readableMessage) {
		return status(false, HttpStatus.BAD_REQUEST, null, errors, readableMessage);
	}

	public static <T> Response<T> notFound(List<String> errors) {
		return notFound(errors, null);
	}

	public static <T> Response<T> notFound(List<String> errors, T data) {
		return status(false, HttpStatus.NOT_FOUND, data, errors);
	}

	public static <T> Response<T> notFound(List<String> errors, String messages) {
		return status(false, HttpStatus.NOT_FOUND, null, errors, messages);
	}

	public static <T> Response<T> notFoundReadableMessage(List<String> errors, String readableMessage) {
		return statusWithReadableMessage(false, HttpStatus.NOT_FOUND, null, errors, readableMessage);
	}

	private static <T> Response<T> status(boolean status, HttpStatus httpStatus, T data, List<String> errors) {
		return Response.<T>builder()
			.status(status)
			.code(String.valueOf(httpStatus.value()))
			.message(httpStatus.name())
			.data(data)
			.errors(errors)
			.build();
	}

	private static <T> Response<T> status(boolean status, HttpStatus httpStatus, T data, List<String> errors,
			String message) {
		return Response.<T>builder()
			.status(status)
			.code(String.valueOf(httpStatus.value()))
			.message(message)
			.data(data)
			.errors(errors)
			.build();
	}

	private static <T> Response<T> statusWithReadableMessage(boolean status, HttpStatus httpStatus, T data,
			List<String> errors, String readableMessage) {
		return Response.<T>builder()
			.status(status)
			.code(String.valueOf(httpStatus.value()))
			.readableMessage(readableMessage)
			.message(httpStatus.name())
			.data(data)
			.errors(errors)
			.build();
	}

	private static <T> Response<T> fail(boolean status, HttpStatus httpStatus, String error) {
		return Response.<T>builder()
			.status(status)
			.code(String.valueOf(httpStatus.value()))
			.message(error)
			.data(null)
			.errors(null)
			.build();
	}

	private static <T> Response<T> success(boolean status, HttpStatus httpStatus, T data, List<String> errors,
			String message) {
		return Response.<T>builder()
			.status(status)
			.code(String.valueOf(httpStatus.value()))
			.message(message)
			.data(data)
			.errors(errors)
			.build();
	}

	public static <T> Response<T> unvailable(String message) {
		return fail(false, HttpStatus.SERVICE_UNAVAILABLE, message);
	}

	public static <T> Response<T> ok(T data, String message) {
		return success(true, HttpStatus.OK, data, null, message);
	}

	public boolean getStatus() {
		return status;
	}

}
