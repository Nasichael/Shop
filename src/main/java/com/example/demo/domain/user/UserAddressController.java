package com.example.demo.domain.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAddressController {
    private final UserAddressService userAddressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }


    @GetMapping("addresses/{id}")
    public Optional<UserAddress> findById(@PathVariable("id") int id) {

        return userAddressService.findById (id);
    }

    @GetMapping("users/{userId}/addresses}")
    public List<UserAddress> findForUser(@PathVariable("id") int userId) {
        return userAddressService.findByUser (userId);
    }


    @DeleteMapping("addresses/{id}")
    public void remove(@PathVariable("id") int id) {
        userAddressService.deleteById (id);
    }

    @PostMapping(value = "addresses")
    public void add(@RequestBody UserAddress userAddress) {
        userAddressService.save (userAddress);
    }


    @PutMapping(value = "addresses/{id}")
    public void edit(@PathVariable int id, @RequestBody UserAddress userAddress) {
        userAddressService.update (id, userAddress);
    }

}
