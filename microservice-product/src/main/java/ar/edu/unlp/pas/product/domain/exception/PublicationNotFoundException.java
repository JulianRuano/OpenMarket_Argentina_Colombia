package ar.edu.unlp.pas.product.domain.exception;

public class PublicationNotFoundException extends RuntimeException  {
     public PublicationNotFoundException(String message) {
        super(message);
    }
}
