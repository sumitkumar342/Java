package com.mb.IPL.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(String msg){
        super(msg);
    }
}
