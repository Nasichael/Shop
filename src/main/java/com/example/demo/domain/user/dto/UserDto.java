package com.example.demo.domain.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    private int id;
    private String name;
}
