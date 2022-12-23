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
@Document(collection = "shops")
public class Shop {
    @Id
    private String id;
    private String name;
    private String userId;
    private String description;
    private String phone;
    private Boolean upgraded = false;
    private boolean disabled = false;
    private List<Rider> riders = new ArrayList<>();
    private List<Reviews> reviews = new ArrayList<>();

    private Date subscriptionStartDate;
    private Date subscriptionEndDate;

    private double longitude;
    private double latitude;
    private Date createdAt;
    private Date updatedAt;
}
