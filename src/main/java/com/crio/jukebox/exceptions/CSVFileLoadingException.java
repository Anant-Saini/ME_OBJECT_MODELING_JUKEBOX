package com.crio.jukebox.exceptions;

public class CSVFileLoadingException extends RuntimeException {

    public CSVFileLoadingException() {}

    public CSVFileLoadingException(String message) {
        super(message);
    }

    public CSVFileLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}
