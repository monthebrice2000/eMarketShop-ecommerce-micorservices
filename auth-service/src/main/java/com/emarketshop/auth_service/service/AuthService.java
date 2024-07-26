package com.emarketshop.auth_service.service;

import com.emarketshop.auth_service.controller.AuthException;
import com.emarketshop.auth_service.dtos.LoginDto;
import com.emarketshop.auth_service.dtos.UserDtoo;
import com.emarketshop.auth_service.dtos.UserHeader;
import com.emarketshop.auth_service.jwt.JWTHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService
        implements IAuthService {

    private final JWTHelper jwtHelper;
    private final IUserServiceClient userServiceClient;

    @Override
    public String login(final LoginDto loginDto) {

        UserDtoo userDto = UserDtoo.builder().name("test").lastName("teso").email("test@gmail.com").username("test").build();

        var user = userDto; // this.userServiceClient.getUserForLogin(loginDto)
                                         // .orElseThrow(() -> new AuthException(AuthException.GENERIC_LOGIN_FAIL));

        try {
            return this.jwtHelper.generateToken(user);
        } catch (JsonProcessingException e) {
            throw new AuthException(AuthException.GENERIC_LOGIN_FAIL);
        }
    }


    @Override
    public UserHeader validateToken(final String jwt) {
        return this.jwtHelper.decodeJWT(jwt)
                             .orElseThrow(() -> new AuthException(
                                     HttpStatus.UNAUTHORIZED,
                                     AuthException.GENERIC_LOGIN_FAIL
                             ));
    }
}
