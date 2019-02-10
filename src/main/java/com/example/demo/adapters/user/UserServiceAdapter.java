package com.example.demo.adapters.user;

import com.example.demo.domain.user.UserRepository;
import com.example.demo.domain.user.UserService;
import com.example.demo.domain.user.dto.UserDto;
import com.example.demo.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceAdapter implements UserService {

    private final UserRepository userRepository;

    public UserServiceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDto> findById(int id) {
        return userRepository.findById (id).map (UserMapper::toDto);
    }


    @Override
    public void save(UserDto userAddressDto) {
        User userAddress = UserMapper.toEntity (userAddressDto);
        userRepository.save (userAddress);
    }


    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll ()
                .stream ()
                .map (UserMapper::toDto)
                .collect (Collectors.toList ());
    }

}
