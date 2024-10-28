package org.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private Long productId;
    private String productName;
    // Các trường khác tương tự ProductEntity
}
