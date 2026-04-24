package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.os.Handler;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.usecase.GetProductListUseCase;
import com.example.conocimientosbasicos_andorid_studio.view.main.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class MainPresenter {
    private MainView mainView;
    private final GetProductListUseCase getProductListUseCase;
    private final MainRouter router;

    private final Handler mainHandler;

    @Inject
    public MainPresenter(GetProductListUseCase getProductListUseCase, MainRouter router, Handler mainHandler) {
        this.getProductListUseCase = getProductListUseCase;
        this.router = router;
        this.mainHandler = mainHandler;
    }

    public void attachView(MainView mainView) {
        this.mainView = mainView;
    }

    public void detachView() {
        this.mainView = null;
    }

    public void loadProductList() {
        mainView.setRefreshing(true);
        mainView.cleanProducts();
        getProductListUseCase.execute(0, 150, products -> {
            mainHandler.post(() -> {
                mainView.setRefreshing(false);
                mainView.showProducts(mapToItemList(products));
            });
        });
    }

    private List<ListItem> mapToItemList(List<Product> products) {
        List<ListItem> listItems = new ArrayList<>();

        if (products != null && !products.isEmpty()) {
            listItems.add(new ListItem.Header("Productos Destacados"));

            int half = products.size() / 2;
            for (int i = 0; i < half; i++) {
                listItems.add(new ListItem.ProductListItem(products.get(i)));
            }

            listItems.add(new ListItem.Header("Resto del Catálogo"));

            for (int i = half; i < products.size(); i++) {
                listItems.add(new ListItem.ProductListItem(products.get(i)));
            }
        }
        return listItems;
    }

    public void onProductClicked(Product product) {
        router.navigateToDetail(product.getId());
    }

    public void showToast(String message) {
        mainView.showToast(message);
    }
}
