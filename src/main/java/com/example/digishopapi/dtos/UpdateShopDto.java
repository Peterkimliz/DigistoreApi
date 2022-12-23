package com.example.digishopapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShopDto {
    private String name;
    private String description;
    private boolean disabled;
    private String phone;
    private String longitude;
    private String latitude;

}
