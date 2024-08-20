package com.example.adviceex.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
