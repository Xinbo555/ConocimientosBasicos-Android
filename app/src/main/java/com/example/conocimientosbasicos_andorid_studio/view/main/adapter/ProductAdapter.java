package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conocimientosbasicos_andorid_studio.databinding.HeaderProductBinding;
import com.example.conocimientosbasicos_andorid_studio.databinding.ItemProductBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import java.util.function.Consumer;

public class ProductAdapter extends ListAdapter<ListItem, RecyclerView.ViewHolder> {

    private final int TYPE_HEADER = 0;
    private final int TYPE_PRODUCT = 1;
    private final ImageLoader imageLoader;
    private Consumer<Product> onProductClickedListener;

    public ProductAdapter(ProductDiffCallback diffCallback, ImageLoader imageLoader, Consumer<Product> onProductClickedListener) {
        super(diffCallback);
        this.imageLoader = imageLoader;
        this.onProductClickedListener = onProductClickedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            HeaderProductBinding binding = HeaderProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new HeaderViewHolder(binding);
        }
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding, imageLoader);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListItem item = getItem(position);
        if (item instanceof ListItem.Header) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            ListItem.Header header = (ListItem.Header) item;
            headerViewHolder.bind(((ListItem.Header) item).getTitle());
        }
        else {
            ProductViewHolder headerViewHolder = (ProductViewHolder) holder;
            ListItem.ProductListItem productItem = (ListItem.ProductListItem) item;
            headerViewHolder.bind(((ListItem.ProductListItem) item).getProduct(),onProductClickedListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof ListItem.Header? TYPE_HEADER : TYPE_PRODUCT;
    }
}
