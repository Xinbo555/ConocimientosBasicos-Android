package com.example.conocimientosbasicos_andorid_studio.data.remote.dto;

import java.util.List;

public class ProductDTO {
    private final Long id;
    private final String title;
    private final String description;
    private final List<String> images;

    public ProductDTO(Long id, String title, String description, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }
}
