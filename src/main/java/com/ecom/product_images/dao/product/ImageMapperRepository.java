package com.ecom.product_images.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface ProductImageMapperRepo extends JpaRepository<ProductImageMapper,String> {
}
