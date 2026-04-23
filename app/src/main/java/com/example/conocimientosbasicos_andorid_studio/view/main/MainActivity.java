package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.conocimientosbasicos_andorid_studio.databinding.ActivityMainBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductAdapter;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductDiffCallback;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainView{

    private ActivityMainBinding binding;
    private ProductAdapter adapter;

    @Inject
    MainPresenter presenter;
    @Inject
    ProductDiffCallback callback;
    @Inject
    ImageLoader imageLoader;
    @Inject
    MainRouter router;

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLauncher();

        attachPresenter();
        initAdapter();
        initRecyclerView();
        initSwipeRefreshLayout();

        initUi();
    }

    private void initLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == MainActivity.RESULT_OK) {
                        presenter.showToast(result.getData().getStringExtra("toast_msg"));
                    }
                });

        router.setLauncher(launcher);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("has salido","has salido");
        presenter.showToast("Has salido de la activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
    private void initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadProductList();
        });
    }

    private void initUi() {
        presenter.loadProductList();
    }

    private void attachPresenter() {
        presenter.attachView(this);
    }

    private void initAdapter() {
        adapter = new ProductAdapter(callback,imageLoader,presenter::onProductClicked);
    }

    private void initRecyclerView() {
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProducts.setAdapter(adapter);
    }

    @Override
    public void showProducts(List<Product> products) {
        adapter.submitList(products);
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        binding.swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void cleanProducts() {
        adapter.submitList(Collections.emptyList());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}