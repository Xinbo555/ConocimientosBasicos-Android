package com.example.conocimientosbasicos_andorid_studio.view.main;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;

import java.util.List;

public interface MainView {
    void showProducts(List<Product> products);
    void setRefreshing(boolean isRefreshing);
    void cleanProducts();
}
