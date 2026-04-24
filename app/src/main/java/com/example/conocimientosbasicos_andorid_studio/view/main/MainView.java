package com.example.conocimientosbasicos_andorid_studio.view.main;

import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ListItem;

import java.util.List;

public interface MainView {
    void showProducts(List<ListItem> listItem);
    void setRefreshing(boolean isRefreshing);
    void cleanProducts();
    void showToast(String message);
}
