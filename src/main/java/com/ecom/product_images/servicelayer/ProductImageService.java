package com.ecom.product_images.servicelayer;

import com.ecom.product_images.dao.product.ProductImageMapper;
import com.ecom.product_images.dao.product.ProductImageMapperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
    @Autowired
    ProductImageMapperRepo productImageMapperRepo;

    public String  createImageMapper(String productId)
    {
       ProductImageMapper imageMapper= new ProductImageMapper();
       imageMapper.setProductImagesId(productId);
       productImageMapperRepo.save(imageMapper);
        return imageMapper.getProductImagesId();
    }


}
