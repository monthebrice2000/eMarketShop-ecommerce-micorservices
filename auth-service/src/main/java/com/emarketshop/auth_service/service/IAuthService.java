package com.emarketshop.auth_service.service;


import com.emarketshop.auth_service.dtos.LoginDto;
import com.emarketshop.auth_service.dtos.UserHeader;

public interface IAuthService {
    String login(LoginDto loginDto);

    UserHeader validateToken(String jwt);
}
