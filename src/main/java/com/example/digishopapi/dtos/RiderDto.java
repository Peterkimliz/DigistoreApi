package com.example.digishopapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDto {
    @NotBlank(message = "provide shopId")
    private String shopId;
    @NotBlank(message = "provide  name")
    private String name;
    @NotBlank(message = "provide phone")
    private String phone;
    @NotBlank(message = "provide email")
    private String email;

}
