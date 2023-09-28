package com.ecom.product_images.servicelayer;

import com.ecom.product_images.ProductImagesApplication;
import com.ecom.product_images.dao.images.ProductImage;
import com.ecom.product_images.dao.product.ImageMapper;
import com.ecom.product_images.dao.product.ImageMapperRepository;
import com.ecom.product_images.exceptions.ImageMapper.ImageMapperExistException;
import com.ecom.product_images.exceptions.ImageMapper.ImageMapperNotExistException;
import com.ecom.product_images.exceptions.productImage.ProductImageExistException;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public String deleteImageMapper(String imageMapperId)
    {
        Optional<ImageMapper> checkMapper=imageMapperRepository.findById(imageMapperId);
        if(checkMapper.isEmpty())throw new ImageMapperNotExistException(imageMapperId);
        imageMapperRepository.deleteById(imageMapperId);
        return imageMapperId;
    }
    public Optional<Set<String>> getImagesImageMapper(String imageMapperId)
    {
        Optional<ImageMapper> imageMapper=imageMapperRepository.findById(imageMapperId);
        if(imageMapper.isEmpty())throw new ImageMapperNotExistException(imageMapperId);

        return Optional.of(imageMapper.get().getImages().keySet());
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
