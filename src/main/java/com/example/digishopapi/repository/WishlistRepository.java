package com.example.digishopapi.repository;

import com.example.digishopapi.models.Product;
import com.example.digishopapi.models.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    Optional<Wishlist> findByUserIdAndAndProduct(String userId, Product product);
    List<Wishlist> findByUserId(String userId);

}
