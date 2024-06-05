package com.najib.Spring_boot_testing.exception;

public class RessourseNotFoundException extends RuntimeException{

    public RessourseNotFoundException(String message){
        super(message);
    }

    public RessourseNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
