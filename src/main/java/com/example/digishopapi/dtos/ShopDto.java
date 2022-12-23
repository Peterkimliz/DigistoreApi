package com.example.digishopapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    @NotBlank(message = "provide name")
    private String name;
    @NotBlank(message = "provide description")
    private String description;
    @NotBlank(message = "provide phone")
    private String phone;
    @NotBlank(message = "provide location")
    private String longitude;
    @NotBlank(message = "provide location")
    private String latitude;
}
