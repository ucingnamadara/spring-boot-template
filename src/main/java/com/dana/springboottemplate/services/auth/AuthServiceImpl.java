package com.dana.springboottemplate.services.auth;

import com.dana.springboottemplate.dto.auth.AuthUserResponse;
import com.dana.springboottemplate.dto.auth.LoginRequest;
import com.dana.springboottemplate.dto.auth.RegisterRequest;
import com.dana.springboottemplate.exceptions.ValidationException;
import com.dana.springboottemplate.model.User;
import com.dana.springboottemplate.repository.UserRepository;
import com.dana.springboottemplate.security.auth.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Base64Utils;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public AuthUserResponse login(LoginRequest request) {
		Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
		if (optionalUser.isEmpty())
			throw new ValidationException("DATA_NOT_FOUND");

		User user = optionalUser.get();

		String hashPassword = Base64Utils.encode(request.getPassword());

		if (!user.getPassword().equals(hashPassword))
			throw new ValidationException("DATA_NOT_FOUND");

		String token = jwtUtils.generateJwtToken(user);
		return mappingAuthUserResponse(user, token);
	}

	@Override
	public AuthUserResponse register(RegisterRequest request) {
		Optional<User> optionalUser = userRepository.findByEmailOrPhoneNumber(request.getEmail(),
				request.getPhoneNumber());
		if (optionalUser.isEmpty())
			throw new ValidationException("DATA_ALREADY_EXISTS");

		String hashPassword = Base64Utils.encode(request.getPassword());

		User newUser = User.builder()
			.email(request.getEmail())
			.phoneNumber(request.getPhoneNumber())
			.password(request.getPassword())
			.build();
		userRepository.save(newUser);

		return mappingAuthUserResponse(newUser, hashPassword);
	}

	private AuthUserResponse mappingAuthUserResponse(User user, String token) {
		return AuthUserResponse.builder()
			.user(AuthUserResponse.User.builder()
				.id(user.getId())
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.build())
			.token(AuthUserResponse.Token.builder().accessToken(token).build())
			.build();
	}

}
