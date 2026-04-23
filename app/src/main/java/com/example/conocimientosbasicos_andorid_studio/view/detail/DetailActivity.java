package com.example.conocimientosbasicos_andorid_studio.view.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.conocimientosbasicos_andorid_studio.R;
import com.example.conocimientosbasicos_andorid_studio.databinding.ActivityDetailBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity implements DetailView{

    private ActivityDetailBinding binding;

    @Inject
    DetailPresenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        attachPresenter();
        initToolbar();
        initUi();

    }

    private void initToolbar() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setTitle(getString(R.string.product_detail));
        }
    }

    private void attachPresenter() {
        presenter.attachView(this);
    }

    private void initUi() {
        presenter.loadProduct(getProductIdFromIntent());
    }

    private Long getProductIdFromIntent() {
        return getIntent().getLongExtra("productId", 0);
    }

    @Override
    public void bind(Product product) {
        binding.tvProduct.setText(product.getTitle());
        binding.tvDescription.setText(product.getDescription());
        imageLoader.loadImage(binding.ivProduct, product.getImage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        presenter.onBackClicked(binding.etToastAlSalir.getText().toString());
        return super.onSupportNavigateUp();
    }
}