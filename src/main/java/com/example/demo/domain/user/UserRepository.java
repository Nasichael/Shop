package com.example.demo.domain.user;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
