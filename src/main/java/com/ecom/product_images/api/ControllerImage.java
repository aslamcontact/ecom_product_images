package com.ecom.product_images.api;

import com.ecom.product_images.servicelayer.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ControllerImage {
    @Autowired
        ProductImageService productImageService;
    @PostMapping("/product/mapper")
    public String createImageMapper(@RequestParam String product)
    {

        return productImageService.createImageMapper(product);
    }
    @PostMapping("/product/mapper/image")
    public String addImageToMapper(@RequestParam String product,
                                   @RequestParam String category,
                                   @RequestBody MultipartFile image
                                   ) throws IOException {
        return productImageService.addImageToMapper(product,category,image.getBytes());
    }
    @GetMapping( value = "/product/mapper/image",
                 produces = MediaType.IMAGE_JPEG_VALUE
                )
    public byte[] getImage(@RequestParam String product,
                           @RequestParam String category)
    {

      return productImageService.getImage(product,category);
    }
}
