package com.ecom.product_images.servicelayer;

import com.ecom.product_images.dao.images.ProductImage;
import com.ecom.product_images.dao.product.ImageMapper;
import com.ecom.product_images.dao.product.ImageMapperRepository;
import com.ecom.product_images.exceptions.ImageMapper.ImageMapperExistException;
import com.ecom.product_images.exceptions.ImageMapper.ImageMapperNotExistException;
import com.ecom.product_images.exceptions.productImage.ProductImageExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImageService {
    @Autowired
    ImageMapperRepository imageMapperRepository;

    public String  createImageMapper(String imageMapperId)
    {
        Optional<ImageMapper> checkImageMapper=imageMapperRepository
                                               .findById(imageMapperId);
        if(checkImageMapper.isPresent())
            throw new ImageMapperExistException(imageMapperId);

       ImageMapper imageMapper= new ImageMapper(imageMapperId);
         ImageMapper result =imageMapperRepository.save(imageMapper);
         return result.getProductImagesId();

    }

    public String addImageToMapper(String id,
                                   String categoryName,
                                   byte[] imageByte)
    {
     Optional<ImageMapper> imageMapper= imageMapperRepository.findById(id);
        if(imageMapper.isEmpty())
            throw new ImageMapperNotExistException(id);
           Optional<ProductImage>  image=Optional.ofNullable( imageMapper
                           .get()
                           .getImages()
                           .get(categoryName));
           if(image.isPresent())
                 throw new ProductImageExistException(id,categoryName);


            imageMapper
                    .get()
                    .getImages()
                    .put(categoryName,new ProductImage(imageByte));



        imageMapperRepository.save(imageMapper.get());

       return categoryName;
    }

    public byte[] getImage(
                     String id,
                     String category
                    )
    {
        Optional<ImageMapper> result;
        result= imageMapperRepository.findById(id);
        if(result.isEmpty())
            throw new ImageMapperNotExistException(id);
        return result.get().getImages().get(category).getImage();
    }



}
