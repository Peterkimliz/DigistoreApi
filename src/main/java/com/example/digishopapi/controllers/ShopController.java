package com.example.digishopapi.controllers;


import com.example.digishopapi.dtos.ShopDto;
import com.example.digishopapi.dtos.UpdateShopDto;
import com.example.digishopapi.models.Shop;
import com.example.digishopapi.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shops")

public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Shop> createShop( @PathVariable("userId") String userId, @RequestBody @Validated ShopDto shopDto) {
        return new ResponseEntity<>(shopService.createShop(userId, shopDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") String id) {
        return new ResponseEntity<>(shopService.getShopById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShopById(@PathVariable("id") String id) {
        shopService.deleteShopById(id);
        return new ResponseEntity<>("shop deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShopById(@PathVariable("id") String id, @RequestBody UpdateShopDto updateShopDto) {
        return new ResponseEntity<>(shopService.updateShopById(id, updateShopDto), HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Shop>> getShops(@RequestParam (required = false) String pageNumber) {
        List<Shop> shops = shopService.getAllShops(pageNumber);
        return new ResponseEntity<>(shops, shops.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }


}
