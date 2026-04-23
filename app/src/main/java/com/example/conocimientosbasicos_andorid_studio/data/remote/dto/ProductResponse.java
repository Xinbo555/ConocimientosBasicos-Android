package com.example.conocimientosbasicos_andorid_studio.data.remote.dto;

import java.util.List;

public class ProductResponse {
    private List<ProductDTO> products;
    private int total;
    private int skip;
    private int limit;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }
}
