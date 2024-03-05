package com.server.dto.users;

import com.server.model.users.Gender;

public record ClientDTO(
        String name,
        String phone,
        String email,
        Gender gender
) {
}
