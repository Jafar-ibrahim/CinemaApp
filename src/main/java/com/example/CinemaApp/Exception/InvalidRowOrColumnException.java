package com.example.CinemaApp.Exception;

import com.example.CinemaApp.Entity.Theater;

public class InvalidRowOrColumnException extends RuntimeException{
    public InvalidRowOrColumnException() {
        super("The row/col value entered is invalid !");
    }

    public InvalidRowOrColumnException(String message) {
        super(message);
    }
}
