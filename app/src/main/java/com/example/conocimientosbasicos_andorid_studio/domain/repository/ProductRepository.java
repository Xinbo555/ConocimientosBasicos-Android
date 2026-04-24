package com.example.conocimientosbasicos_andorid_studio.domain.repository;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;

import java.util.List;
import java.util.function.Consumer;

public interface ProductRepository {
    List<Product> getProductList(int skip, int limit);
    Product getProductById(Long id);
}
