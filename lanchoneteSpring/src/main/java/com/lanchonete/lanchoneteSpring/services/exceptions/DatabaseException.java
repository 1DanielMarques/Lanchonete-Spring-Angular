package com.lanchonete.lanchoneteSpring.services.exceptions;

public class DatabaseException extends RuntimeException {

    private static final long serialVerionUID = 1L;

    public DatabaseException(String msg) {
        super(msg);
    }

}
