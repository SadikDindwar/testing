package com.apiexamples.exception;

public class ResourceNotFound extends RuntimeException{

    //If the exception occurs after running the project then it is called runtime exception
    //so our custom exception class will extend  RuntimeException.

    public ResourceNotFound(String message){
        super(message);
    }


}
