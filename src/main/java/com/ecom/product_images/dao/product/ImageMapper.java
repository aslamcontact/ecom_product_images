package com.ecom.product_images.dao.product;

import com.ecom.product_images.dao.images.ProductImage;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
public class ProductImageMapper {
    @Id
    private String productImagesId;
    @ElementCollection
    Map<String,ProductImage> images;


    public ProductImageMapper() {
    }

    public ProductImageMapper(String productImagesId, Map<String, ProductImage> images) {
        this.productImagesId = productImagesId;
        this.images = images;
    }

    public String getProductImagesId() {
        return productImagesId;
    }

    public void setProductImagesId(String productImagesId) {
        this.productImagesId = productImagesId;
    }

    public Map<String, ProductImage> getImages() {
        return images;
    }

    public void setImages(Map<String, ProductImage> images) {
        this.images = images;
    }
}
