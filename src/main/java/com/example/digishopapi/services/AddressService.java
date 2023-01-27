package com.example.digishopapi.services;

import com.example.digishopapi.dtos.AddressDto;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.Address;
import com.example.digishopapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserService userService;

    public Address createAddess(AddressDto addressDto, String userId) {
        userService.getUserById(userId);
        Address address = new Address();
        address.setUserId(userId);
        address.setCity(addressDto.getCity());
        address.setName(address.getName());
        address.setCountry(addressDto.getCountry());
        address.setPhone(addressDto.getPhone());
        address.setCreatedAt(new Date(System.currentTimeMillis()));
        address.setUpdatedAt(new Date(System.currentTimeMillis()));
        return addressRepository.save(address);
    }

    public List<Address> getAddressByUserId(String userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        if (addresses.size() == 0) {
            return new ArrayList<>();
        } else {
            return addresses;
        }
    }

    public Address getAddressById(String id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            return address.get();
        } else {
            throw new NotFoundException("address with the id doesnot exist");
        }
    }

    public Address updateAddressById(String id, Address address) {
        Optional<Address> foundaddress = addressRepository.findById(id);
        if (foundaddress.isPresent()) {
            Address savedAddress = foundaddress.get();
            savedAddress.setCity(address.getCity() == null ? savedAddress.getCity() : address.getCity());
            savedAddress.setName(address.getName() == null ? savedAddress.getName() : address.getName());
            savedAddress.setCountry(address.getCountry() == null ? savedAddress.getCountry() : address.getCountry());
            savedAddress.setPhone(address.getPhone() == null ? savedAddress.getPhone() : address.getPhone());
            savedAddress.setUpdatedAt(new Date(System.currentTimeMillis()));
            return addressRepository.save(savedAddress);

        } else {
            throw new NotFoundException("address with the id doesnot exist");
        }
    }

    public void deleteAddressById(String id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.deleteById(id);
        } else {
            throw new NotFoundException("address with the id doesnot exist");
        }
    }


}
