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
@Document(collection = "address")
public class Address {
    @Id
    private String id;
    private String name;
    private  String country;
   private String state;
   private String city;
   private String phone;
   private String userId;
   private Date createdAt;
   private Date updatedAt;


}
