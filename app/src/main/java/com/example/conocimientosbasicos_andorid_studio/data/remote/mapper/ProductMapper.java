package com.example.conocimientosbasicos_andorid_studio.data.remote.mapper;

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
}
