package com.emarketshop.auth_service.service;

import com.emarketshop.auth_service.controller.AuthException;
import com.emarketshop.auth_service.dtos.LoginDto;
import com.emarketshop.auth_service.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceClient
        implements IUserServiceClient {

    private final RestTemplate restTemplate;

    @Value("${api.user-service}")
    private String userServiceUrl;

    @Override
    public Optional<UserDto> getUserForLogin(final LoginDto loginDto) {
        try {

            var url = this.userServiceUrl + "/login";

            var response = this.restTemplate.postForEntity(url, loginDto, UserDto.class);

            if (response.getStatusCode()
                        .isError()) {
                throw new AuthException(AuthException.GENERIC_LOGIN_FAIL);
            }

            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
