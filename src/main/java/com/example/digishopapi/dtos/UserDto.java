package com.example.digishopapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "please enter email")
    private String email;
    @NotBlank(message = "please enter username")
    private String username;
    @NotBlank(message = "please enter password")
    private String password;
    @NotBlank(message = "please enter phone")
    private String phone;
    @NotBlank(message = "please enter userType")
    private String userType;

}
