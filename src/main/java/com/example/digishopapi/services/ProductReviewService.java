package com.example.digishopapi.services;

import com.example.digishopapi.dtos.ReviewsDto;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.models.ProductReviews;
import com.example.digishopapi.models.Reviews;
import com.example.digishopapi.models.User;
import com.example.digishopapi.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProductReviewService {
    @Autowired
    private  ProductReviewRepository productReviewRepository;
    @Autowired
    UserService userService;

    public ProductReviews createReview(String productId, ReviewsDto reviewsDto) {
        User user=userService.getUserById(reviewsDto.getUserId());
        ProductReviews foundReview = productReviewRepository.findByUserId(user);

        if (foundReview != null) {
            throw new FoundException(" you have reviewed this product already");
        } else {
            ProductReviews reviews = new ProductReviews();
            reviews.setCreatedAt(new Date(System.currentTimeMillis()));
            reviews.setUpdatedAt(new Date(System.currentTimeMillis()));
            reviews.setReview(reviewsDto.getReview());
            reviews.setUserId(user);
            reviews.setRating(Integer.parseInt(reviewsDto.getRating()));
            reviews.setProductId(productId);
            return productReviewRepository.save(reviews);
        }


    }

    public List<ProductReviews> getProductReviews(String productId) {
        List<ProductReviews> reviews = productReviewRepository.findByProductId(productId);
        if (reviews.size() == 0) {
            return new ArrayList<>();
        } else {
            return reviews;
        }
    }

}
