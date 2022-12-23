package com.example.digishopapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsDto {
    @NotBlank(message = "provide review message")
    private String review;
    @NotBlank(message = "provide review rating")
    private String rating;
    @NotBlank(message = "provide userId")
    private String userId;
    @NotBlank(message = "provide shopId")
    private String shopId;



}
