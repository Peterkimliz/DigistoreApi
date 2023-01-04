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

    public Reviews createReview(String shopId, ReviewsDto reviewsDto) {
        Shop shop = shopService.getShopById(shopId);
        Reviews foundReview = reviewsRepository.findByShopIdAndUserId(shopId, reviewsDto.getUserId());

        if (shop == null) {
            throw new NotFoundException("shop with id not found");
        } else if (foundReview != null) {
            throw new FoundException(" you have reviewed this shop already");
        } else {
            Reviews reviews = new Reviews();
            reviews.setCreatedAt(new Date(System.currentTimeMillis()));
            reviews.setUpdatedAt(new Date(System.currentTimeMillis()));
            reviews.setReview(reviewsDto.getReview());
            reviews.setShopId(shopId);
            reviews.setRating(Integer.parseInt(reviewsDto.getRating()));
            reviews.setUserId(reviews.getUserId());
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
