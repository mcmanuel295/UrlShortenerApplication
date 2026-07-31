package com.mcmanuel.UrlShortener.exception;

public class OriginalUrlNotFoundException extends RuntimeException {

    public OriginalUrlNotFoundException(String message){
        super(message);
    }
}
