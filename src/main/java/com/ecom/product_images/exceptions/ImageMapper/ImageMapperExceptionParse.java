package com.ecom.product_images.exceptions.ImageMapper;

import org.springframework.http.HttpStatus;

public class ImageMapperExceptionParse {
    private final String message;
    private final HttpStatus httpStatus;
    private String payLoad;

    public ImageMapperExceptionParse(String message, HttpStatus httpStatus,String payLoad) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.payLoad=payLoad;
    }

    public String getMessage() {
        return message;
    }



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getPayLoad() {
        return payLoad;
    }
}
