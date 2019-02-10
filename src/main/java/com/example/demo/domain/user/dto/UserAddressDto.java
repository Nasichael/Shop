package com.example.demo.domain.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserAddressDto {

    private int id;
    private String street;
}
