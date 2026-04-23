package com.example.conocimientosbasicos_andorid_studio.view.detail;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class DetailRouter {
    private final Context context;

    @Inject
    public DetailRouter(@ActivityContext Context context) {
        this.context = context;
    }

    public void goBack() {
        if(context instanceof Activity) {
            ((Activity) context).onBackPressed();
        }
    }
}
