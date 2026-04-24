package com.example.conocimientosbasicos_andorid_studio.domain.usecase;

import android.os.Handler;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import javax.inject.Inject;

public class GetProductUseCase {
    private final ProductRepository productRepository;
    private final ExecutorService executorService;
    private final Handler mainHandler;
    @Inject
    public GetProductUseCase(ProductRepository productRepository, ExecutorService executorService, Handler mainHandler) {
        this.productRepository = productRepository;
        this.executorService = executorService;
        this.mainHandler = mainHandler;
    }

    public void execute(Long id, Consumer<Product> onProductLoaded) {
        executorService.execute(() -> {
            Product product = productRepository.getProductById(id);

            mainHandler.post(() -> {
                onProductLoaded.accept(product);
            });
        });
    }
}
