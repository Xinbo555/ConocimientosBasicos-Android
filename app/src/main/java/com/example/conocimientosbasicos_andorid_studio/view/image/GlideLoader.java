package com.example.conocimientosbasicos_andorid_studio.view.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class GlideLoader implements ImageLoader{
    private final Context context;

    @Inject
    public GlideLoader(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
