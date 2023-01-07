package com.example.digishopapi.dtos;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "please enter product name")
    private String name;
    @NotBlank(message = "please enter description")
    private String description;
    @NotBlank(message = "please enter price")
    private String  price ;
    @NotBlank(message = "please enter quantity")
    private  String quantity;
    @NotBlank(message = "please enter category")
    private String category;
    @NotEmpty(message = "enter product image")
    private List<String> images;


}
