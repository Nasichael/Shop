package com.example.demo.adapters.user;

import com.example.demo.domain.user.AddressRepository;
import com.example.demo.domain.user.UserAddressService;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.domain.user.dto.UserAddressDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.adapters.user.UserAddressMapper.toDto;

@Service
public class UserAddressServiceAdapter implements UserAddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public UserAddressServiceAdapter(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserAddressDto> findById(int id) {
        return addressRepository.findById (id).map (UserAddressMapper::toDto);
    }

    @Override
    public List<UserAddressDto> findByUser(int userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById (id);
    }

    @Override
    public UserAddressDto save(int userId, UserAddressDto userAddressDto) {
        UserAddress userAddress = UserAddressMapper.toEntity (userAddressDto);
        final Optional<User> byId = userRepository.findById (userId);
        User user = byId.orElseThrow (() -> new RuntimeException ("Given User does not exist"));
        userAddress.setUser (user);
        return UserAddressMapper.toDto (addressRepository.save (userAddress));
    }

    @Override
    public UserAddressDto update(int id, UserAddressDto userAddress) {
        final Optional<UserAddress> oldAddress = addressRepository.findById (id);
        final UserAddress userAddress1 = oldAddress
                .map (a -> copyFields (a, userAddress))
                .orElseThrow (() -> new RuntimeException ("no entity found"));
        final UserAddress save = addressRepository.save (userAddress1);
        return toDto (save);
    }

    @Override
    public List<UserAddressDto> findAll() {
        return addressRepository.findAll ()
                .stream ()
                .map (UserAddressMapper::toDto)
                .collect (Collectors.toList ());
    }

    private UserAddress copyFields(UserAddress original, UserAddressDto newAddress) {
        original.setStreet (newAddress.getStreet ());
        return original;

    }
}
