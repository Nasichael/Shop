package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.entity.UserAddress;

import java.util.List;
import java.util.Optional;

public interface UserAddressService {
    Optional<UserAddressDto> findById(int id);

    List<UserAddressDto> findByUser(int userId);

    void deleteById(int id);

    UserAddressDto save(int userId, UserAddressDto userAddress);

    UserAddressDto update(int id, UserAddressDto userAddress);

    List<UserAddressDto> findAll();
}
