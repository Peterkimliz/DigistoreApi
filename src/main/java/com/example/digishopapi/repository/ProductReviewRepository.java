package com.example.digishopapi.repository;

import com.example.digishopapi.models.ProductReviews;
import com.example.digishopapi.models.Reviews;
import com.example.digishopapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductReviewRepository extends MongoRepository<ProductReviews,String> {
    ProductReviews findByUserId(User userid);
}
