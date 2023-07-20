package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Application.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public UserEntity mapModelToData(User user){

        return new UserEntity(
                user.getPseudo(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword())
        );
    }

    public User mapDataToModel(UserEntity userEntity){

        return new User(
                userEntity.getPseudo(),
                userEntity.getEmail(),
                passwordEncoder.encode(userEntity.getPassword())
        );
    }
}
