package ar.edu.unlp.pas.product.domain.exception;

public class InsufficientStockException extends RuntimeException  {
     public InsufficientStockException(String message) {
        super(message);
    }
}
