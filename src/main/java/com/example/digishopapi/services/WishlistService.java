package com.example.digishopapi.services;

import com.example.digishopapi.models.Product;
import com.example.digishopapi.models.Wishlist;
import com.example.digishopapi.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    WishlistRepository wishlistRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    public String createWishlist(String userId, String productId) {
        userService.getUserById(userId);
        Product product = productService.getProductById(productId);
        Optional<Wishlist> foundWishlist = wishlistRepository.findByUserIdAndAndProduct(userId, product);
        if (foundWishlist.isPresent()) {
            wishlistRepository.deleteById(foundWishlist.get().getId());
            userService.updateWishList(productId, userId);
            return "product removed from wishlist";

        } else {
            Wishlist wishlist = new Wishlist();
            wishlist.setCreatedAt(new Date(System.currentTimeMillis()));
            wishlist.setUpdatedAt(new Date(System.currentTimeMillis()));
            wishlist.setUserId(userId);
            wishlist.setProduct(product);
            wishlistRepository.save(wishlist);
            userService.updateWishList(productId, userId);
            return "product added to wishlist";

        }


    }


    public List<Wishlist> getUserWishList(String userId) {
        List<Wishlist> wishlists = wishlistRepository.findByUserId(userId);
        if (wishlists.size() == 0) {
            return new ArrayList<>();
        } else {
            System.out.print(wishlists);  
            return wishlists;
        }
    }

}
