package com.example.digishopapi.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
//    @Min(message = "please add atleast one image", value = 1)
    private List<String> images;
    @NotBlank(message = "please enter category")
    private String category;

}
