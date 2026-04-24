package com.example.conocimientosbasicos_andorid_studio.di;

import android.content.Context;

import androidx.room.Room;

import com.example.conocimientosbasicos_andorid_studio.data.local.dao.ProductDao;
import com.example.conocimientosbasicos_andorid_studio.data.local.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "productos_db"
        ).build();
    }

    @Provides
    public ProductDao provideProductDao(AppDatabase database) {
        return database.productDao();
    }
}
