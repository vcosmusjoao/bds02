package com.devsuperior.bds02.services.excepetion;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String msg){
        super(msg);
    }

}
