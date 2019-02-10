package com.example.demo.domain.user;

import com.example.demo.domain.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddress, Integer> {
}
