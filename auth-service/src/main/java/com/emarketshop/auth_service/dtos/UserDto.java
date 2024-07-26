package com.emarketshop.auth_service.dtos;

import java.io.Serializable;

public record UserDto(
                Long id,
                String name,
                String lastName,
                String email,
                String username)
                implements Serializable {
}