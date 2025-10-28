package com.crio.jukebox.exceptions;

public class PlayListIsEmptyException extends RuntimeException {

    public PlayListIsEmptyException() {}

    public PlayListIsEmptyException(String message) {
        super(message);
    }

    public PlayListIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
