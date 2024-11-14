package org.example.test.service;

import org.example.test.dto.request.ProductRequest;
import org.example.test.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<ProductEntity> findAll();

    int save(ProductRequest productRequest);

    Optional<ProductEntity> findById(int id);

    void update(int id, ProductRequest productRequest);

    void delete(int id);

}
