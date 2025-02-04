package com.bookstore.order_service.exception;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String msg) {
        super(msg);
    }

    public static InvalidOrderException forCode(String code) {
        return new InvalidOrderException("Invalid Product code: "+ code);
    }
}
