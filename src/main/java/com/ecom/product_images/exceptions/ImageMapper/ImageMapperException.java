package com.ecom.product_images.exceptions.ImageMapper;

public class ImageMapperException extends RuntimeException{

    private final String payLoad;

    public ImageMapperException(String message,String payLoad)
    {
        super(message);
        this.payLoad=payLoad;
    }

    public ImageMapperException(String payLoad,String message, Throwable cause) {
        super(message, cause);
        this.payLoad=payLoad;
    }

    public String getPayLoad() {
        return payLoad;
    }
}
