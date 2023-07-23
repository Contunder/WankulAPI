package com.contunder.wankulapi.Application.Controller;

import com.contunder.wankulapi.Application.Model.User;
import com.contunder.wankulapi.Data.Payload.JWTAuthResponse;
import com.contunder.wankulapi.Application.Service.AuthService;
import com.contunder.wankulapi.Data.Payload.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDTO user) {
        String token = authService.login(user);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody User user){

        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

}