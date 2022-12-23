package com.example.digishopapi.dtos;

import com.example.digishopapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {
    User user;
    String token;
}
