package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.User;
import com.contunder.wankulapi.Data.Entity.UserEntity;

public interface UserService {
    UserEntity getUserEntity(String email);

    UserEntity getUserEntityByPseudo(String pseudo);

    User getUser(String email);

    User getUserByPseudo(String pseudo);
}
