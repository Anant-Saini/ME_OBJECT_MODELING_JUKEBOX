package com.crio.jukebox.exceptions;

public class SongNotFoundInPlayListException extends RuntimeException {

    public SongNotFoundInPlayListException() {}

    public SongNotFoundInPlayListException(String message) {
        super(message);
    }

    public SongNotFoundInPlayListException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
