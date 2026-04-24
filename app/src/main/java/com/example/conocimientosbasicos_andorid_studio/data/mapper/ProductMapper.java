package com.example.conocimientosbasicos_andorid_studio.data.mapper;

import com.example.conocimientosbasicos_andorid_studio.data.local.entity.ProductEntity;
import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductDTO;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;

public class ProductMapper {
    public static Product toDomain(ProductDTO productDTO){
        return new Product(
            productDTO.getId(),
            productDTO.getTitle(),
            productDTO.getDescription(),
            productDTO.getImages().get(0)
        );
    }

    public static Product toDomain(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImage()
        );
    }

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getImage()
        );
    }
}
