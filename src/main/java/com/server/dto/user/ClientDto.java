package com.server.dto.user;

import com.server.model.user.Gender;

public record ClientDto(
        String name,
        String phone,
        String email,
        Gender gender
) {
}
