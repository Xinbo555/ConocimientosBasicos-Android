package com.example.conocimientosbasicos_andorid_studio.di;

import com.example.conocimientosbasicos_andorid_studio.data.repository.ProductRepositoryImpl;
import com.example.conocimientosbasicos_andorid_studio.domain.repository.ProductRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    @Binds
    @Singleton
    public abstract ProductRepository bindProductRepository(ProductRepositoryImpl productRepository);
}
