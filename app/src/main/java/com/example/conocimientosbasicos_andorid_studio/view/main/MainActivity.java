package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.conocimientosbasicos_andorid_studio.R;
import com.example.conocimientosbasicos_andorid_studio.databinding.ActivityMainBinding;
import com.example.conocimientosbasicos_andorid_studio.databinding.FragmentMainBinding;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ListItem;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductAdapter;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductDiffCallback;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, MainFragment.class, null)
                    .commit();
        }
    }
}