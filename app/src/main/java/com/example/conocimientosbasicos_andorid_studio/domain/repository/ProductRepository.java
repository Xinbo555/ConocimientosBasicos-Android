package com.example.conocimientosbasicos_andorid_studio.domain.repository;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;

import java.util.List;
import java.util.function.Consumer;

public interface ProductRepository {
    void getProductList(int skip, int limit, Consumer<List<Product>> onProductsLoaded);
    void getProductById(Long id, Consumer<Product> onProductLoaded);
}
