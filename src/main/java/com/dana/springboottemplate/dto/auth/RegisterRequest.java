package com.dana.springboottemplate.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

	private String email;

	private String phoneNumber;

	private String password;

}
