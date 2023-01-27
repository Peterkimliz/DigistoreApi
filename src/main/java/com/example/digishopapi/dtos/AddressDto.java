package com.example.digishopapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotBlank(message = "name required ")
    private String name;
    @NotBlank(message = "country required ")
    private  String country;
    @NotBlank(message = "state required ")
    private String state;
    @NotBlank(message = "city required ")
    private String city;
    @NotBlank(message = "phone required ")
    private String phone;
}
