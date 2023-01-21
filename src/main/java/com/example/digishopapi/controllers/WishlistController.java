package com.example.digishopapi.controllers;

import com.example.digishopapi.models.Wishlist;
import com.example.digishopapi.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist/")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @PutMapping("{productId}/{userId}")
    public ResponseEntity<String> updateWishList(@PathVariable("productId") String productId, @PathVariable("userId") String userId) {
        return new ResponseEntity<>(wishlistService.createWishlist(userId, productId), HttpStatus.OK);
    }
    @GetMapping("all/{userId}")
    public ResponseEntity<List<Wishlist>> getUserWishList(@PathVariable("userId") String userId) {
        List<Wishlist> wishlists=wishlistService.getUserWishList(userId);
        return new ResponseEntity<>(wishlists,wishlists.size()==0?HttpStatus.NOT_FOUND: HttpStatus.OK);
    }
}
