package com.dana.springboottemplate.services.auth;

import com.dana.springboottemplate.dto.auth.AuthUserResponse;
import com.dana.springboottemplate.dto.auth.LoginRequest;
import com.dana.springboottemplate.dto.auth.RegisterRequest;

public interface AuthService {

	AuthUserResponse login(LoginRequest request);

	AuthUserResponse register(RegisterRequest request);

}
