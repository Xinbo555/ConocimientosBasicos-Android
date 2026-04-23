package com.example.conocimientosbasicos_andorid_studio.data.remote.api;

import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductDTO;
import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApiService {

    @GET("products")
    Call<ProductResponse> listProducts(
            @Query("skip") int skip,
            @Query("limit") int limit
    );

    @GET("products/{id}")
    Call<ProductDTO> getProductById(@Path("id") Long id);
}
