package com.ecom.product_images.api;

import com.ecom.product_images.dao.product.ImageMapper;
import com.ecom.product_images.servicelayer.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ControllerImage {
    @Autowired
        ProductImageService productImageService;
    @PostMapping("/product")
    public ResponseEntity<String> createImageMapper(@RequestParam String id)
    {

        String createdId = productImageService.createImageMapper(id);
        return new ResponseEntity<>(
                                     createdId,
                                     HttpStatus.CREATED
                                   );
    }
    @PostMapping("/product/image")
    public ResponseEntity<String> addImageToMapper(
                                   @RequestParam String id,
                                   @RequestParam String category,
                                   @RequestBody MultipartFile image
                                   ) throws IOException {
       String imageName= productImageService.addImageToMapper(
                                                              id,
                                                              category,
                                                              image.getBytes()
                                                               );

       return new ResponseEntity<>(imageName,HttpStatus.CREATED);
    }
    @GetMapping( value = "/product/image",
                 produces = MediaType.IMAGE_JPEG_VALUE
                )
    public byte[] getImage(@RequestParam String id,
                           @RequestParam String category)
    {

      return productImageService.getImage(id,category);
    }
}
