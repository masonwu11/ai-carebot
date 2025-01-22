package com.aicarebot.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aicarebot.api.daos.LoginResponse;
import com.aicarebot.api.daos.LoginUserDao;
import com.aicarebot.api.daos.RegisterUserDto;
import com.aicarebot.api.daos.User;
import com.aicarebot.api.services.AuthenticationService;
import com.aicarebot.api.services.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  private AuthenticationService authenticationService;

  private final JwtService jwtService;

  public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
    this.authenticationService = authenticationService;
    this.jwtService = jwtService;
  }

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    logger.info("Signup request received with the following data: {}", registerUserDto.toString());
    boolean validationResult = authenticationService.validate(registerUserDto) == null ? false : true;
    if (validationResult) {
      logger.warn("User with email: {} already exists", registerUserDto.getEmail());
      return ResponseEntity.status(409).build();
    }
    User registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate (@RequestBody LoginUserDao LoginUserDto) {
    logger.info("Login request received with the following data: {}", LoginUserDto.toString());
    User user = authenticationService.authenticate(LoginUserDto);
    String token = jwtService.generateToken(user);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(token);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);
  }

}
