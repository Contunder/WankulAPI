package com.contunder.wankulapi.Application.Service.impl;

import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import com.contunder.wankulapi.Application.Model.User;
import com.contunder.wankulapi.Application.Service.UserService;
import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Data.Mapper.UserMapper;
import com.contunder.wankulapi.Data.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.CARD_NOT_FOUND;
import static com.contunder.wankulapi.Application.Enum.MessageConstant.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    @Override
    public UserEntity getUserEntity(String email){

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
    }

    @Override
    public UserEntity getUserEntityByPseudo(String pseudo){

        return userRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
    }

    @Override
    public User getUser(String email){

        return userMapper.mapDataToModel(userRepository.findByEmail(email)
                .orElseThrow(() -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)));
    }

    @Override
    public User getUserByPseudo(String pseudo){

        return userMapper.mapDataToModel(userRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new WankulAPIException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)));
    }
}
