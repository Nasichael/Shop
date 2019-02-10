package com.example.demo.adapters.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.entity.UserAddress;

public class UserAddressMapper {
    public static UserAddressDto toDto(UserAddress from) {
        return UserAddressDto.builder ()
                .id (from.getId ())
                .street (from.getStreet ())
                .build ();
    }

    public static UserAddress toEntity(UserAddressDto from) {
        UserAddress userAddress = new UserAddress ();
        userAddress.setId (from.getId ());
        userAddress.setStreet (from.getStreet ());
        return userAddress;
    }
}
