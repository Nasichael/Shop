package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<UserAddress, Integer> {
    List<UserAddressDto> findByUserId(int userId);
}
