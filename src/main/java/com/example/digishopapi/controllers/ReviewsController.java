package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.ReviewsDto;
import com.example.digishopapi.models.Reviews;
import com.example.digishopapi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/shop/{shopId}")
    public ResponseEntity<Reviews> createReviews(@PathVariable("shopId") String shopId, @RequestBody ReviewsDto reviewsDto) {
        return new ResponseEntity<>(reviewService.createReview(shopId, reviewsDto), HttpStatus.CREATED);
    }

    @GetMapping("all/{shopId}")
    public ResponseEntity<List<Reviews>> getShopReviews(@PathVariable("shopId") String shopId) {
        List<Reviews> reviews = reviewService.getShopReviews(shopId);
        return new ResponseEntity<>(reviews, reviews.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
