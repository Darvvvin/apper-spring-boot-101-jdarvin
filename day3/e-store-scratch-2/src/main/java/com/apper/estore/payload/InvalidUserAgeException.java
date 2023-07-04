package com.apper.estore.payload;

public class InvalidUserAgeException extends Exception{
    public InvalidUserAgeException(String message){
        super(message);
    }
}
