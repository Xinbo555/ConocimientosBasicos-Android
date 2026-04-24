package com.example.conocimientosbasicos_andorid_studio.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.conocimientosbasicos_andorid_studio.data.local.dao.ProductDao;
import com.example.conocimientosbasicos_andorid_studio.data.local.entity.ProductEntity;

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
