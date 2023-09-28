package com.ecom.product_images.servicelayer;

import com.ecom.product_images.dao.images.ProductImage;
import com.ecom.product_images.dao.product.ImageMapper;
import com.ecom.product_images.dao.product.ImageMapperRepository;
import com.ecom.product_images.exceptions.ImageMapper.ImageMapperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImageService {
    @Autowired
    ImageMapperRepository imageMapperRepository;

    public String  createImageMapper(String productId)
    {
        Optional<ImageMapper> imageMapperCheck=imageMapperRepository.findById(productId);
        if(imageMapperCheck.isPresent())
            throw new ImageMapperException("image mapper is already used",productId);
       ImageMapper imageMapper= new ImageMapper();
       imageMapper.setProductImagesId(productId);
       imageMapperRepository.save(imageMapper);
        return imageMapper.getProductImagesId();
    }

    public String addImageToMapper(String productName,
                                   String imageType,
                                   byte[] imageByte)
    {
     Optional<ImageMapper> product= imageMapperRepository.findById(productName);
        if(product.isEmpty())
            throw new IllegalStateException("therw");
        product.get().getImages().put(imageType,new ProductImage(imageByte));
       return imageMapperRepository.save(product.get()).getProductImagesId();
    }

    public byte[] getImage(
                     String product,
                     String category
                    )
    {
        Optional<ImageMapper> result;
        result= imageMapperRepository.findById(product);
        if(result.isEmpty())throw  new IllegalStateException("there is no product");
        return result.get().getImages().get(category).getImage();
    }



}
