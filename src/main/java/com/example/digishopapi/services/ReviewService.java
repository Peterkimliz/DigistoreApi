package com.example.digishopapi.services;

import com.example.digishopapi.dtos.ReviewsDto;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.Reviews;
import com.example.digishopapi.models.Shop;
import com.example.digishopapi.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    ShopService shopService;
    @Autowired
    UserService userService;

    public Reviews createReview(String shopId, ReviewsDto reviewsDto) {
        shopService.getShopById(shopId);
        System.out.println(reviewsDto.getUserId());
        userService.getUserById(reviewsDto.getUserId());
        Reviews foundReview = reviewsRepository.findByShopIdAndUserId(shopId, reviewsDto.getUserId());

        if (foundReview != null) {
            throw new FoundException(" you have reviewed this shop already");
        } else {
            Reviews reviews = new Reviews();
            reviews.setCreatedAt(new Date(System.currentTimeMillis()));
            reviews.setUpdatedAt(new Date(System.currentTimeMillis()));
            reviews.setReview(reviewsDto.getReview());
            reviews.setShopId(shopId);
            reviews.setRating(Integer.parseInt(reviewsDto.getRating()));
            reviews.setUserId(reviewsDto.getUserId());
            return reviewsRepository.save(reviews);
        }


    }

    public List<Reviews> getShopReviews(String shopId) {
        List<Reviews> reviews = reviewsRepository.findByShopId(shopId);
        if (reviews.size() == 0) {
            return new ArrayList<>();
        } else {
            return reviews;
        }
    }

}
