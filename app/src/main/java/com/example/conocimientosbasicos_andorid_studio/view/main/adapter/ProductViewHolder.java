package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.conocimientosbasicos_andorid_studio.databinding.ItemProductBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import java.util.function.Consumer;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private final ItemProductBinding binding;
    private final ImageLoader imageLoader;
    public ProductViewHolder(ItemProductBinding binding, ImageLoader imageLoader) {
        super(binding.getRoot());
        this.binding = binding;
        this.imageLoader = imageLoader;
    }

    public void bind(Product product, Consumer<Product> onProductClickListener) {
        binding.tvProduct.setText(product.getTitle());
        imageLoader.loadImage(binding.ivProduct, product.getImage());
        binding.ivProduct.setOnClickListener(view -> onProductClickListener.accept(product));
    }
}
