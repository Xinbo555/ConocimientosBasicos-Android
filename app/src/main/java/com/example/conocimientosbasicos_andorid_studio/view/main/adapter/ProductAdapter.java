package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.conocimientosbasicos_andorid_studio.databinding.ItemProductBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import java.util.function.Consumer;

public class ProductAdapter extends ListAdapter<Product, ProductViewHolder> {
    private final ImageLoader imageLoader;
    private Consumer<Product> onProductClickedListener;

    public ProductAdapter(ProductDiffCallback diffCallback, ImageLoader imageLoader, Consumer<Product> onProductClickedListener) {
        super(diffCallback);
        this.imageLoader = imageLoader;
        this.onProductClickedListener = onProductClickedListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemProductBinding binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new ProductViewHolder(binding, imageLoader);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(getItem(position), onProductClickedListener);
    }
}
