package org.example.test.mapper.product;

import org.example.test.dto.request.ProductRequest;
import org.example.test.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "imageFileName", ignore = true)
    ProductRequest toProductRequest(ProductEntity productEntity);

    @Mapping(
            target = "id", ignore = true
    )
    @Mapping(
            target = "createdAt", ignore = true
    )
    @Mapping(
            target = "imageFileName", ignore = true
    )
    ProductEntity toProductEntity(ProductRequest productRequest);
}
