package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAddressController {
    private final UserAddressService userAddressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @GetMapping("addresses")
    public List<UserAddressDto> findAll() {
        return userAddressService.findAll ();
    }


    @GetMapping("addresses/{id}")
    public Optional<UserAddressDto> findById(@PathVariable("id") int id) {
        return userAddressService.findById (id);
    }

    @GetMapping(value = "users/{userId}/addresses")
    public List<UserAddressDto> findForUser(@PathVariable("userId") int userId) {
        return userAddressService.findByUser (userId);
    }


    @DeleteMapping("addresses/{id}")
    public void remove(@PathVariable("id") int id) {
        userAddressService.deleteById (id);
    }

    @PostMapping(value = "users/{userId}/addresses")
    public UserAddressDto add(@PathVariable int userId, @RequestBody UserAddressDto userAddress) {
        return userAddressService.save (userId, userAddress);
    }


    @PutMapping(value = "addresses/{id}")
    public void edit(@PathVariable int id, @RequestBody UserAddressDto userAddress) {
        userAddressService.update (id, userAddress);
    }

}
