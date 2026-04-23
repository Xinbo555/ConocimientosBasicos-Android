package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;

import com.example.conocimientosbasicos_andorid_studio.view.detail.DetailActivity;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class MainRouter {
    private final Context context;

    private ActivityResultLauncher<Intent> launcher;

    @Inject
    public MainRouter(@ActivityContext Context context) {
        this.context = context;
    }

    public void setLauncher(ActivityResultLauncher<Intent> launcher) {
        this.launcher = launcher;
    }

    public void navigateToDetail(Long productId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("productId", productId);

        if (launcher != null) {
            launcher.launch(intent); // Usar el launcher
        } else {
            context.startActivity(intent); // Fallback
        }
    }
}
