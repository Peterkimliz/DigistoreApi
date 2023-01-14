package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.ReviewsDto;
import com.example.digishopapi.models.ProductReviews;
import com.example.digishopapi.services.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/reviews/product/")
public class ProductReviewsController {

    @Autowired
    ProductReviewService productReviewService;

    @PostMapping("create/{productId}")
    public ResponseEntity<ProductReviews> createReviews(@PathVariable("productId") String productId, @RequestBody @Validated ReviewsDto reviewsDto) {
        return new ResponseEntity<>(productReviewService.createReview(productId, reviewsDto), HttpStatus.CREATED);
    }
    @GetMapping("all/{productId}")
    public ResponseEntity<List<ProductReviews>> getShopReviews(@PathVariable("productId") String productId) {
        List<ProductReviews> reviews = productReviewService.getProductReviews(productId);
        return new ResponseEntity<>(reviews, reviews.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
