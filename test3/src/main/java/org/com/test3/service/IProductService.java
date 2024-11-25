package org.com.test3.service;

import org.com.test3.entiy.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    void deleteProduct(Long id);
    Product getProduct(Long id);
    Product saveProduct(Product product);
    List<Product> getProducts();
}
