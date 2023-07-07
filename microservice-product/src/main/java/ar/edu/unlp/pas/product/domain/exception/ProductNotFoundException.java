package ar.edu.unlp.pas.product.domain.exception;

public class ProductNotFoundException extends RuntimeException  {
     public ProductNotFoundException(String message) {
        super(message);
    }
}
