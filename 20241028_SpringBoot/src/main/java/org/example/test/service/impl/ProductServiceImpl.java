package org.example.test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.request.ProductRequest;
import org.example.test.entity.ProductEntity;
import org.example.test.mapper.product.ProductMapper;
import org.example.test.repository.ProductRepository;
import org.example.test.service.IProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductRequest toProductRequest(ProductEntity productEntity) {
        return productMapper.toProductRequest(productEntity);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public int save(ProductRequest productRequest) {
        return productRepository.save(toProductEntity(productRequest, false, null)).getId();
    }

    @Override
    public Optional<ProductEntity> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(int id, ProductRequest productRequest) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            ProductEntity product = toProductEntity(productRequest, true, productEntity.get());
            product.setId(id);
            productRepository.save(product);
        }
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public ProductEntity toProductEntity(
            ProductRequest productRequest, boolean isUpdate, ProductEntity existingProduct
    ) {
        ProductEntity productEntity = isUpdate ? existingProduct : new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setBrand(productRequest.getBrand());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setCreatedAt(new Date());

        if (productRequest.getImageFileName() != null && !productRequest.getImageFileName().isEmpty()) {
            productEntity.setImageFileName(saveImage(productRequest, isUpdate));
        } else if (isUpdate) {
            productEntity.setImageFileName(existingProduct.getImageFileName());
        }

        return productEntity;
    }

    public String saveImage(ProductRequest productRequest, boolean isUpdate) {
        String UPLOAD_DIR = "public/images/";
        MultipartFile image = productRequest.getImageFileName();
        String fileName = new Date().getTime() + "_" + image.getOriginalFilename();

        try {
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            path = Paths.get(UPLOAD_DIR + fileName);
            try (var inputStream = image.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            log.error("Failed to save image", e);
        }

        return fileName;
    }
}