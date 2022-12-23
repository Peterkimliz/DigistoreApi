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
@Document(collection = "Riders")
public class Rider {
    @Id
    private String id;
    private String shopId;
    private String name;
    private String phone;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
