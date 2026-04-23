package com.example.conocimientosbasicos_andorid_studio.view.main;

import android.os.Handler;
import android.util.Log;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.domain.usecase.GetProductListUseCase;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class MainPresenter {
    private MainView mainView;
    private final GetProductListUseCase getProductListUseCase;

    private final MainRouter router;

    @Inject
    Handler mainHandler;

    @Inject
    public MainPresenter(GetProductListUseCase getProductListUseCase, MainRouter router) {
        this.getProductListUseCase = getProductListUseCase;
        this.router = router;
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
        getProductListUseCase.execute(0,30,products -> {
            mainView.setRefreshing(false);
            mainView.showProducts(products);
        });
    }

    public void navigateFromMainActivityToDetailActivity(Product product) {
        router.navigateToDetail(product.getId());
    }

}
