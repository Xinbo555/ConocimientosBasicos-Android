package com.example.conocimientosbasicos_andorid_studio.view.main.adapter;

import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;

public abstract class ListItem {
    private ListItem() {
    }

    public static final class Header extends ListItem {
        private final String title;

        public Header(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public static final class ProductListItem extends ListItem {

        private final Product product;

        public ProductListItem(Product product) {
            this.product = product;
        }

        public Product getProduct() {
            return product;
        }
    }
}
