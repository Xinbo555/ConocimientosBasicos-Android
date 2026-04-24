package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.conocimientosbasicos_andorid_studio.databinding.HeaderProductBinding;
import com.example.conocimientosbasicos_andorid_studio.databinding.ItemProductBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import java.util.function.Consumer;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private final HeaderProductBinding binding;
    public HeaderViewHolder(HeaderProductBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String title) {
        binding.tvHeader.setText(title);
    }
}
