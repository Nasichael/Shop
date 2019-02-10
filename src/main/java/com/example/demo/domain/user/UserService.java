package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findById(int id);

    void save(UserDto userAddress);

    List<UserDto> findAll();
}
