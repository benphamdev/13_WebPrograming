package org.example.test.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private Long categoryId;

    @NotBlank(message = "Category name is required")
    @Size(max = 200, message = "Category name must be less than or equal to 200 characters")
    private String name;

    private Set<ProductRequest> products;

    private boolean isEdit;
}
