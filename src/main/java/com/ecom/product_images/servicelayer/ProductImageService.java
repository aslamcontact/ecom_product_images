package com.ecom.product_images.servicelayer;

import com.ecom.product_images.dao.images.ProductImage;
import com.ecom.product_images.dao.product.ProductImageMapper;
import com.ecom.product_images.dao.product.ProductImageMapperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public String addImageToMapper(String productName,
                                   String imageType,
                                   byte[] imageByte)
    {
     Optional<ProductImageMapper> product= productImageMapperRepo.findById(productName);
        if(product.isEmpty())
            throw new IllegalStateException("there is no product in database");
        product.get().getImages().put(imageType,new ProductImage(imageByte));
       return productImageMapperRepo.save(product.get()).getProductImagesId();
    }

    public byte[] getImage(
                     String product,
                     String category
                    )
    {
        Optional<ProductImageMapper> result;
        result=productImageMapperRepo.findById(product);
        if(result.isEmpty())throw  new IllegalStateException("there is no product");
        return result.get().getImages().get(category).getImage();
    }



}
