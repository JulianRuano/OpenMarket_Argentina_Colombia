package ar.edu.unlp.pas.product.domain.exception;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException(String message) {
        super(message);
    }
}
