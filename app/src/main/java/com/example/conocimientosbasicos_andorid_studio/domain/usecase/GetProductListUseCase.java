package com.example.conocimientosbasicos_andorid_studio.domain.usecase;

import android.os.Handler;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import javax.inject.Inject;

public class GetProductListUseCase {

    private final ProductRepository productRepository;
    private final ExecutorService executorService;
    private final Handler mainHandler;

    @Inject
    public GetProductListUseCase(ProductRepository productRepository, ExecutorService executorService, Handler mainHandler) {
        this.productRepository = productRepository;
        this.executorService = executorService;
        this.mainHandler = mainHandler;
    }

    public void execute(int skip, int limit, Consumer<List<Product>> onProductsLoaded){
        executorService.execute(() -> {
            List<Product> products = productRepository.getProductList(skip,limit);

            mainHandler.post(() -> {
                onProductsLoaded.accept(products);
            });
        });
    }
}
