package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<UserAddress, Integer> {
    @Query("SELECT a FROM UserAddress a WHERE a.user.id = ?1")
    List<UserAddress> findByUserId(int userId);

}
