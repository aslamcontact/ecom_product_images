package com.ecom.product_images.api;

import com.ecom.product_images.dao.product.ImageMapper;
import com.ecom.product_images.servicelayer.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.spec.OAEPParameterSpec;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ControllerImage {
    @Autowired
        ProductImageService productImageService;
    @PostMapping("/product/image/mapper/{id}")
    public ResponseEntity<String> createImageMapper(@PathVariable("id") String id)
    {

        String createdId = productImageService.createImageMapper(id);
        return new ResponseEntity<>(
                                     createdId,
                                     HttpStatus.CREATED
                                   );
    }

    @GetMapping("/product/image/mapper/{id}")
    public ResponseEntity<Optional<Set<String>>> getAllImageFromMapper(@PathVariable("id") String id)
    {
         return new ResponseEntity<>(
                                      productImageService.getImagesImageMapper(id),
                                      HttpStatus.FOUND
                                    );
    }
    @DeleteMapping("/product/image/mapper/{id}")
    public ResponseEntity<String> deleteImageMapper(@PathVariable("id") String id)
    {
        return new ResponseEntity<>(
                                     productImageService.deleteImageMapper(id),
                                      HttpStatus.OK
                                   );
    }




    @PostMapping("/product/image/mapper/{id}/{category}")
    public ResponseEntity<String> addImageToMapper(
                                   @PathVariable("id") String id,
                                   @PathVariable("category") String category,
                                   @RequestBody MultipartFile image
                                   ) throws IOException {
       String imageName= productImageService.addImageToMapper(
                                                              id,
                                                              category,
                                                              image.getBytes()
                                                               );

       return new ResponseEntity<>(imageName,HttpStatus.CREATED);
    }
    @GetMapping( value = "/product/image/mapper/{id}/{category}",
                 produces = MediaType.IMAGE_JPEG_VALUE
                )
    public ResponseEntity<byte[]> getImageFromMapper(@PathVariable("id") String id,
                           @PathVariable("category") String category)
    {

      byte[] image=productImageService.getImageFromMapper(id,category);
      return new ResponseEntity<>(image,HttpStatus.FOUND);
    }
    @DeleteMapping("/product/image/mapper/{id}/{category}")
    public ResponseEntity<String> removeImageFromMapper(
            @PathVariable("id") String id,
            @PathVariable("category") String category
    )
    {

           productImageService.removeImageFromMapper(id,category);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
}
