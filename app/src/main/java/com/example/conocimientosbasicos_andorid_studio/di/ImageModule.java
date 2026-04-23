package com.example.conocimientosbasicos_andorid_studio.di;

import android.content.Context;

import com.example.conocimientosbasicos_andorid_studio.view.image.GlideLoader;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class ImageModule {

    @Binds
    @Singleton
    public abstract ImageLoader bindGlideLoader(GlideLoader glideLoader);

}
