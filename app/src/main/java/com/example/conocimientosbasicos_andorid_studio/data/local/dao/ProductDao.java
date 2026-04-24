package com.example.conocimientosbasicos_andorid_studio.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.conocimientosbasicos_andorid_studio.data.local.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    List<ProductEntity> getAll();

    @Query("select * from products p where p.id = (:id)")
    Optional<ProductEntity> getProductById(Long id);

    @Insert
    void insertAll(ProductEntity... products);
}
