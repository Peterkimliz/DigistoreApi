package com.example.digishopapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String email;
    private String username;
    private String profileImage="";
    @JsonIgnore
    private String password;
    private String phone;
    private String userType;
    private Date createdAt;
    private Date updatedAt;
}
