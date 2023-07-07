package ar.edu.unlp.pas.delivery.domain.service;

import ar.edu.unlp.pas.delivery.application.ports.input.IDeliveryManager;
import ar.edu.unlp.pas.delivery.application.ports.output.IDeliveryOutputPort;
import ar.edu.unlp.pas.delivery.domain.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService implements IDeliveryManager {

    private final IDeliveryOutputPort  deliveryOutputPort;

    @Autowired
    public DeliveryService(IDeliveryOutputPort deliveryOutputPort) {
        this.deliveryOutputPort = deliveryOutputPort;
    }


    @Override
    public Delivery createDelivery(Delivery delivery) {
        delivery=deliveryOutputPort.createDelivery(delivery);
        return delivery;
    }

    @Override
    public List<Delivery> getPendingOrders() {
        return deliveryOutputPort.getPerdingOrders();
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryOutputPort.getAll();
    }

    @Override
    public Delivery takeOrder(Long idDelivery) {
        return deliveryOutputPort.takeOrder(idDelivery);
    }

    @Override
    public Delivery deliverOrder(Long idDelivery) {
        return deliveryOutputPort.deliverOrder(idDelivery);
    }

    @Override
    public void cancelOrder(Long idDelivery) {
         deliveryOutputPort.cancelOrder(idDelivery);
    }
}
