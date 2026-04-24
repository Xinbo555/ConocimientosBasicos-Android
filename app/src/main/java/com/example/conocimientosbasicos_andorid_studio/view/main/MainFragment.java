package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conocimientosbasicos_andorid_studio.databinding.FragmentMainBinding;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ListItem;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductAdapter;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ProductDiffCallback;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment implements MainView{

    private FragmentMainBinding binding;
    private ProductAdapter adapter;

    private ActivityResultLauncher<Intent> launcher;

    @Inject
    MainPresenter presenter;
    @Inject
    ProductDiffCallback callback;
    @Inject
    ImageLoader imageLoader;
    @Inject
    MainRouter router;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        initLauncher();

        attachPresenter();
        initAdapter();
        initRecyclerView();
        initSwipeRefreshLayout();

        initUi();
        return binding.getRoot();
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
    public void onPause() {
        super.onPause();
        Log.e("has salido","has salido");
        presenter.showToast("Has salido de la activity");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvProducts.setAdapter(adapter);
    }

    @Override
    public void showProducts(List<ListItem> listItem) {
        adapter.submitList(listItem);
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}