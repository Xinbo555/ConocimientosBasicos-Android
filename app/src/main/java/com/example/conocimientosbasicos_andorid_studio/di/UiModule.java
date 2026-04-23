package com.example.conocimientosbasicos_andorid_studio.di;

import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductDiffCallback;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UiModule {

    @Provides
    @Singleton
    public ProductDiffCallback provideProductDiffCallback() {
        return new ProductDiffCallback();
    }
}
