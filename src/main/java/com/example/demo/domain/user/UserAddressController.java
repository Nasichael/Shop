package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.entity.UserAddress;
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

    @GetMapping("users/{userId}/addresses}")
    public List<UserAddressDto> findForUser(@PathVariable("id") int userId) {
        return userAddressService.findByUser (userId);
    }


    @DeleteMapping("addresses/{id}")
    public void remove(@PathVariable("id") int id) {
        userAddressService.deleteById (id);
    }

    @PostMapping(value = "addresses")
    public void add(@RequestBody UserAddressDto userAddress) {
        userAddressService.save (userAddress);
    }




    @PutMapping(value = "addresses/{id}")
    public void edit(@PathVariable int id, @RequestBody UserAddressDto userAddress) {
        userAddressService.update (id, userAddress);
    }

}
