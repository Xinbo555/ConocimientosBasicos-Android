package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.content.Context;
import android.content.Intent;

import com.example.conocimientosbasicos_andorid_studio.view.detail.DetailActivity;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class MainRouter {
    private final Context context;

    @Inject
    public MainRouter(@ActivityContext Context context) {
        this.context = context;
    }

    public void navigateToDetail(Long productId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }
}
