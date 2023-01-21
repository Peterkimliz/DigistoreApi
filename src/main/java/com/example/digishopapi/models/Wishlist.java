package com.example.digishopapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wishlist")
public class Wishlist {
    @Id
    private String id;
    private String userId;
    private Product product;
    private Date createdAt;
    private Date UpdatedAt;
}
