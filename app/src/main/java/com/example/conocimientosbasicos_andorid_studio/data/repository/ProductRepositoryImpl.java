package com.example.conocimientosbasicos_andorid_studio.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.conocimientosbasicos_andorid_studio.data.remote.api.ProductApiService;
import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductDTO;
import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductResponse;
import com.example.conocimientosbasicos_andorid_studio.data.remote.mapper.ProductMapper;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductApiService productApiService;

    @Inject
    public ProductRepositoryImpl(ProductApiService productApiService) {
        this.productApiService = productApiService;
    }

    @Override
    public void getProductList(int skip, int limit, Consumer<List<Product>> onProductsLoaded) {
        productApiService.listProducts(skip,limit)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            List<Product> products = response.body().getProducts().stream().map(ProductMapper::toDomain).collect(Collectors.toList());
                            Log.w("hilo actual", Thread.currentThread().getName());
                            onProductsLoaded.accept(products);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable t) {
                    }
                });
    }

    @Override
    public void getProductById(Long id, Consumer<Product> onProductLoaded) {
        productApiService.getProductById(id)
                .enqueue(new Callback<ProductDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductDTO> call, @NonNull Response<ProductDTO> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            onProductLoaded.accept(ProductMapper.toDomain(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductDTO> call, @NonNull Throwable t) {
                    }
                });
    }


}
