package com.contunder.wankulapi.Application.Service.impl;


import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import com.contunder.wankulapi.Data.Mapper.UserMapper;
import com.contunder.wankulapi.Application.Model.User;
import com.contunder.wankulapi.Data.Payload.LoginDTO;
import com.contunder.wankulapi.Data.Repository.UserRepository;
import com.contunder.wankulapi.Application.Security.JwtTokenProvider;
import com.contunder.wankulapi.Application.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.USER_ALREADY_EXIST;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            throw new WankulAPIException(HttpStatus.BAD_REQUEST, USER_ALREADY_EXIST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(userMapper.mapModelToData(user));

        return "User registered successfully!.";
    }
}
