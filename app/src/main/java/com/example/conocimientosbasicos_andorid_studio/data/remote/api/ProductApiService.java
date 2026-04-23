package com.example.conocimientosbasicos_andorid_studio.data.remote.api;

import com.example.conocimientosbasicos_andorid_studio.data.remote.dto.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {

    @GET("products")
    Call<List<ProductResponse>> listProducts(
            @Query("skip") int skip,
            @Query("limit") int limit
    );

    @GET("products/{id}")
    Call<List<ProductResponse>> getProductById(@Path("id") Long id);
}
