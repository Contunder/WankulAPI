package com.contunder.wankulapi.Application.Service.impl;


import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import com.contunder.wankulapi.Data.Mapper.UserMapper;
import com.contunder.wankulapi.Application.Model.User;
import com.contunder.wankulapi.Data.Payload.LoginDTO;
import com.contunder.wankulapi.Data.Repository.UserRepository;
import com.contunder.wankulapi.Application.Security.JwtTokenProvider;
import com.contunder.wankulapi.Application.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginDTO user) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public String register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new WankulAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        userRepository.save(userMapper.mapModelToData(user));

        return "User registered successfully!.";
    }
}
