package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.AddressDto;
import com.example.digishopapi.models.Address;
import com.example.digishopapi.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address/")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping("create/{userId}")
    public ResponseEntity<Address> createProduct(@RequestBody @Validated AddressDto addressDto, @PathVariable("userId") String userId) {
        return new ResponseEntity<Address>(addressService.createAddess(addressDto,userId), HttpStatus.CREATED);
    }
    @GetMapping("all/{userId}")
    public ResponseEntity<List<Address>> getProductsByShopId(@PathVariable("userId") String userId, @RequestParam(required = false) String pageNumber) {
        List<Address> address = addressService.getAddressByUserId(userId);
        return new ResponseEntity<List<Address>>(address, address.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{addressId}")
    public ResponseEntity<Address> getProductsByCategory(@PathVariable("addressId") String addressId) {

        return new ResponseEntity<Address>(addressService.getAddressById(addressId), HttpStatus.OK);
    }

    @DeleteMapping("{addressId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("addressId") String addressId) {
        addressService.deleteAddressById(addressId);
        return new ResponseEntity<String>("address deleted successfully", HttpStatus.OK);
    }
    @PutMapping("{addressId}")
    public ResponseEntity<Address> updateProductById(@PathVariable("addressId") String addressId,@RequestBody Address address) {

        return new ResponseEntity<Address>(addressService.updateAddressById(addressId,address), HttpStatus.OK);
    }
}
