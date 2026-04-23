package com.example.conocimientosbasicos_andorid_studio.view.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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

    public void goBack(String message) {
        if(context instanceof Activity) {
            if (message != null && !message.isBlank()) {
                Intent intent = new Intent();
                intent.putExtra("toast_msg", message);
                ((Activity) context).setResult(Activity.RESULT_OK, intent);
            }
                ((Activity) context).onBackPressed();
        }
    }

}
