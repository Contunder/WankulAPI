package com.contunder.wankulapi.Data.Mapper;

import com.contunder.wankulapi.Data.Entity.UserEntity;
import com.contunder.wankulapi.Application.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {


    public UserEntity mapModelToData(User user){

        return new UserEntity(
                user.getPseudo(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public User mapDataToModel(UserEntity userEntity){

        return new User(
                userEntity.getPseudo(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }
}
