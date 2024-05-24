package com.dana.springboottemplate.controller;

import com.dana.springboottemplate.dto.auth.AuthUserResponse;
import com.dana.springboottemplate.dto.Response;
import com.dana.springboottemplate.dto.auth.LoginRequest;
import com.dana.springboottemplate.dto.auth.RegisterRequest;
import com.dana.springboottemplate.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<AuthUserResponse> login(@RequestBody LoginRequest request) {
		return Response.ok(authService.login(request));
	}

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<AuthUserResponse> register(@RequestBody RegisterRequest request) {
		return Response.ok(authService.register(request));
	}

}
