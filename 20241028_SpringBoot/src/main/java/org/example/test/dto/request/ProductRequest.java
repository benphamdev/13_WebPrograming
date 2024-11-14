package org.example.test.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequest {
    @NotEmpty(message = "Product name is required")
    private String name;

    @NotEmpty(message = "Product brand is required")
    private String brand;

    @NotEmpty(message = "Product category is required")
    private String category;

    @Min(value = 0, message = "Product price must be greater than or equal to 0")
    private double price;

    @Size(max = 500, message = "Product description must be less than or equal to 500 characters")
    @Size(min = 10, message = "Product description must be greater than or equal to 10 characters")
    private String description;

    private MultipartFile imageFileName;
}


