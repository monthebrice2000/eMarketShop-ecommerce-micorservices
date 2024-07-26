package com.emarketshop.auth_service.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record LoginDto(String username, @NotNull String password) implements Serializable {
}