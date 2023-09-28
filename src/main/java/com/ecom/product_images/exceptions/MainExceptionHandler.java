package com.ecom.product_images.exceptions;

import com.ecom.product_images.exceptions.ImageMapper.ImageMapperException;
import com.ecom.product_images.exceptions.ImageMapper.ImageMapperExceptionParse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler {

     @ExceptionHandler
    public ResponseEntity<Object> imageMapperNotFound(ImageMapperException e)
     {
         HttpStatus httpStatus=HttpStatus.CONFLICT;
         ImageMapperExceptionParse exception=new ImageMapperExceptionParse(
                                                 e.getMessage(),
                                                 httpStatus,
                                                 e.getPayLoad()
                                                 );
         return new ResponseEntity<>(exception,httpStatus);

     }

}
