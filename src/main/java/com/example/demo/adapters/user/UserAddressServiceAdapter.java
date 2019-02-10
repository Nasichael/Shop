package com.example.demo.adapters.user;

import com.example.demo.domain.user.AddressRepository;
import com.example.demo.domain.user.UserAddress;
import com.example.demo.domain.user.UserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceAdapter implements UserAddressService {

    private final AddressRepository addressRepository;

    public UserAddressServiceAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<UserAddress> findById(int id) {
        return addressRepository.findById (id);
    }

    @Override
    public List<UserAddress> findByUser(int userId) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById (id);
    }

    @Override
    public void save(UserAddress userAddress) {
        addressRepository.save (userAddress);
    }

    @Override
    public UserAddress update(int id, UserAddress userAddress) {
        final Optional<UserAddress> oldAddress = addressRepository.findById (id);
        final UserAddress userAddress1 = oldAddress
                .map (a -> copyFields (a, userAddress))

                .orElseThrow (()-> new RuntimeException ("no entity found"));
        final UserAddress save = addressRepository.save (userAddress1);
        return save;
    }

    private UserAddress copyFields(UserAddress a, UserAddress userAddress) {
          a.setStreet (userAddress.getStreet ());
          return a;

    }
}
