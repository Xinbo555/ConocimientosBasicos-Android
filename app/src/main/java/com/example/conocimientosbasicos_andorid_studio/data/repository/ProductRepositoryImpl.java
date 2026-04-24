package com.example.conocimientosbasicos_andorid_studio.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.conocimientosbasicos_andorid_studio.data.local.dao.ProductDao;
import com.example.conocimientosbasicos_andorid_studio.data.local.entity.ProductEntity;
import com.example.conocimientosbasicos_andorid_studio.data.remote.api.ProductApiService;
import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductDTO;
import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductResponse;
import com.example.conocimientosbasicos_andorid_studio.data.mapper.ProductMapper;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductApiService productApiService;
    private final ProductDao productDao;

    @Inject
    public ProductRepositoryImpl(ProductApiService productApiService, ProductDao productDao) {
        this.productApiService = productApiService;
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProductList(int skip, int limit) {
        List<ProductEntity> productEntities = productDao.getAll();

        if(!productEntities.isEmpty()) {
            Log.i("Carga de productos","se ha cargado los productos desde room");
            return productEntities.stream()
                    .map(ProductMapper::toDomain)
                    .collect(Collectors.toList());

        }
        try {
            Log.i("Carga de productos","Room vacío, llamando a la API");
            Response<ProductResponse> response = productApiService.listProducts(skip, limit).execute();

            if(response.isSuccessful() && response.body() != null) {
                List<Product> remoteProducts = response.body().getProducts().stream()
                        .map(ProductMapper::toDomain)
                        .collect(Collectors.toList());

                //Guardamos datos a la room para la próxima vez
                ProductEntity[] entities = remoteProducts.stream()
                        .map(ProductMapper::toEntity)
                        .toArray(ProductEntity[]::new);

                productDao.insertAll(entities);

                return remoteProducts;
            }
        } catch (IOException ignored) {}

        return Collections.emptyList();
    }

    @Override
    public Product getProductById(Long id) {

        Optional<ProductEntity> productEntity = productDao.getProductById(id);

        if(productEntity.isPresent()) {
            Log.i("Carga de productos","se ha cargado el producto desde room");
            return productEntity.map(ProductMapper::toDomain).get();
        }

        try {
            Log.i("Carga de productos","Room vacío, llamando a la API");
            Response<ProductDTO> response = productApiService.getProductById(id)
                    .execute();

            if(response.isSuccessful() && response.body() != null) {
                Product remoteProduct = ProductMapper.toDomain(response.body());

                productDao.insertAll(ProductMapper.toEntity(remoteProduct));

                return remoteProduct;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
