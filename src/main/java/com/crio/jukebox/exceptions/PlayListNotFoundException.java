package com.crio.jukebox.exceptions;

public class PlayListNotFoundException extends RuntimeException {

    public PlayListNotFoundException() {}

    public PlayListNotFoundException(String message) {
        super(message);
    }

    public PlayListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}
