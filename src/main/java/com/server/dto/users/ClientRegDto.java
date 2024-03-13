package com.server.dto.users;

public record ClientRegDto(
        String name,
        String phone,
        String password
) {
}
