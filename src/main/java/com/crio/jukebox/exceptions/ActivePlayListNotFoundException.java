package com.crio.jukebox.exceptions;

public class ActivePlayListNotFoundException extends RuntimeException {

    public ActivePlayListNotFoundException() {}

    public ActivePlayListNotFoundException(String message) {
        super(message);
    }

    public ActivePlayListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
