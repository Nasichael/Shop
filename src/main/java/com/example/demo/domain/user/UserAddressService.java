package com.example.demo.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserAddressService {
    Optional<UserAddress> findById(int id);

    List<UserAddress> findByUser(int userId);

    void deleteById(int id);

    void save(UserAddress userAddress);

    UserAddress update(int id, UserAddress userAddress);
}
