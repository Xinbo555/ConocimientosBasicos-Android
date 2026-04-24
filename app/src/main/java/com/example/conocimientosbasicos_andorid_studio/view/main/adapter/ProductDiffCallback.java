package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class ProductDiffCallback extends DiffUtil.ItemCallback<ListItem> {

    @Override
    public boolean areItemsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
        if (oldItem instanceof ListItem.Header && newItem instanceof ListItem.Header) {
            return oldItem.equals(newItem);
        }
        if (oldItem instanceof ListItem.ProductListItem && newItem instanceof ListItem.ProductListItem) {
            return Objects.equals(((ListItem.ProductListItem) oldItem).getProduct().getId(), ((ListItem.ProductListItem) newItem).getProduct().getId());
        } else return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
        return Objects.equals(oldItem, newItem);
    }
}
