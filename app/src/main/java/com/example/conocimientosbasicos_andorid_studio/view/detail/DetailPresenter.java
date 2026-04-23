package com.example.conocimientosbasicos_andorid_studio.view.detail;

import com.example.conocimientosbasicos_andorid_studio.domain.usecase.GetProductUseCase;

import javax.inject.Inject;

public class DetailPresenter {
    private final GetProductUseCase getProductUseCase;
    private DetailView detailView;

    private final DetailRouter router;
    @Inject
    public DetailPresenter(GetProductUseCase getProductUseCase, DetailRouter router) {
        this.getProductUseCase = getProductUseCase;
        this.router = router;
    }

    public void loadProduct(Long id) {
        getProductUseCase.execute(id, detailView::bind);
    }

    public void attachView(DetailView detailView){
        this.detailView = detailView;
    }

    public void detachView() {
        this.detailView = null;
    }

    public void onBackClicked(String message) {
        router.goBack(message);
    }
}
