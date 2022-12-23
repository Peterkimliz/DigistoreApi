package com.example.digishopapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private int price ;
    private  int quantity;
    private List<String > images;
    private Category category;
    private String ShopId;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted=false;
}
