package ar.edu.unlp.pas.delivery.domain.exception;

public class DeliveryNotFound extends RuntimeException{
    public DeliveryNotFound(String message){
        super(message);
    }
}
