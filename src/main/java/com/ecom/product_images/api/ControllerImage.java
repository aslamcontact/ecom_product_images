package com.ecom.product_images.api;

import com.ecom.product_images.servicelayer.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ControllerImage {
    @Autowired
        ProductImageService productImageService;
    @GetMapping("/image")
    public String createImageMapper(@RequestParam String product)
    {

        return productImageService.createImageMapper(product);
    }
}
