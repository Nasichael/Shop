package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<UserDto> findAll() {
        return userService.findAll ();
    }


    @GetMapping("users/{id}")
    public Optional<UserDto> findById(@PathVariable("id") int id) {
        return userService.findById (id);
    }



    @PostMapping(value = "users")
    public void add(@RequestBody UserDto userAddress) {
        userService.save (userAddress);
    }



}
