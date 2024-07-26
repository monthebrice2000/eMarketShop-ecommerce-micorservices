package com.emarketshop.auth_service.service;

import com.emarketshop.auth_service.dtos.LoginDto;
import com.emarketshop.auth_service.dtos.UserDto;

import java.util.Optional;

public interface IUserServiceClient {
    Optional<UserDto> getUserForLogin(final LoginDto loginDto);
}
