package com.example.conocimientosbasicos_andorid_studio.domain.usecase;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import java.util.function.Consumer;

import javax.inject.Inject;

public class GetProductUseCase {
    private final ProductRepository productRepository;

    @Inject
    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long id, Consumer<Product> onProductLoaded) {
        productRepository.getProductById(id, onProductLoaded);
    }
}
