package com.example.digishopapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Reviews")
public class Reviews {
    private String id;
    private String review;
    private int rating;
    private String userId;
    private String shopId;
    private Date createdAt;
    private Date updatedAt;

}
