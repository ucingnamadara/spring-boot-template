package com.dana.springboottemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

	private Boolean status;

	private String code;

	private String message;

	private T data;

	public static BaseResponse<Object> builder(HttpStatus httpStatus, String message, Object data) {
		if (httpStatus.isError()) {
			return new BaseResponse<>(false, "" + httpStatus.value(), message, data);
		}
		return new BaseResponse<>(true, "" + httpStatus.value(), message, data);
	}

	public static <T> BaseResponse<T> builder(HttpStatus httpStatus, Object data) {
		return (BaseResponse<T>) builder(httpStatus, httpStatus.series().toString(), data);
	}

}
