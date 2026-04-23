package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;

import java.util.Objects;

public class ProductDiffCallback extends DiffUtil.ItemCallback<Product> {

    @Override
    public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem.equals(newItem);
    }
}
