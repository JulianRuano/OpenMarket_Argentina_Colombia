package ar.edu.unlp.pas.delivery.application.ports.input;

import ar.edu.unlp.pas.delivery.domain.model.Delivery;

import java.util.List;

public interface IDeliveryManager {
    Delivery createDelivery(Delivery delivery);
    List<Delivery> getPendingOrders();
    List<Delivery>getAll();
    Delivery takeOrder(Long idDelivery);
    Delivery deliverOrder(Long idDelivery);
    void  cancelOrder(Long idDelivery);

}
