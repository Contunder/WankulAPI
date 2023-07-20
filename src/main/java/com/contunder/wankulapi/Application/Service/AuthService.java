package com.contunder.wankulapi.Application.Service;

import com.contunder.wankulapi.Application.Model.User;
import com.contunder.wankulapi.Data.Payload.LoginDTO;

public interface AuthService {
    String login(LoginDTO user);

    String register(User user);
}
