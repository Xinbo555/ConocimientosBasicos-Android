package com.example.conocimientosbasicos_andorid_studio.domain.usecase;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

public class GetProductListUseCase {

    private final ProductRepository productRepository;

    @Inject
    public GetProductListUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(int skip, int limit, Consumer<List<Product>> onProductsLoaded){
        productRepository.getProductList(skip,limit,onProductsLoaded);
    }
}
