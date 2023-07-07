package ar.edu.unlp.pas.delivery.application.ports.output;

import ar.edu.unlp.pas.delivery.domain.model.Delivery;

import java.util.List;

public interface IDeliveryOutputPort {
    Delivery createDelivery(Delivery delivery);
    List<Delivery> getPerdingOrders();
    Delivery takeOrder(Long idDelivery);
    Delivery deliverOrder(Long idDelivery);
    void cancelOrder(Long idDelivery);
    List<Delivery>getAll();

}
