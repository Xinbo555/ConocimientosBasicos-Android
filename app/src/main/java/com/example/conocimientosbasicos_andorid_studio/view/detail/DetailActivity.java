package com.example.conocimientosbasicos_andorid_studio.view.detail;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.conocimientosbasicos_andorid_studio.R;
import com.example.conocimientosbasicos_andorid_studio.databinding.ActivityDetailBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;
import com.example.conocimientosbasicos_andorid_studio.view.main.MainFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, DetailFragment.class, null)
                    .commit();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if(fragment != null) {
            fragment.handleBackAction();
        }
        return super.onSupportNavigateUp();
    }
}