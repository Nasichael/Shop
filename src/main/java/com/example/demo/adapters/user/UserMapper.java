package com.example.demo.adapters.user;

import com.example.demo.domain.user.dto.UserDto;
import com.example.demo.domain.user.entity.User;

public class UserMapper {
    public static UserDto toDto(User from) {
        return UserDto.builder ()
                .id (from.getId ())
                .name (from.getName ())
                .build ();
    }

    public static User toEntity(UserDto from) {
        User user = new User ();
        user.setId (from.getId ());
        user.setName (from.getName ());
        return user;
    }
}
