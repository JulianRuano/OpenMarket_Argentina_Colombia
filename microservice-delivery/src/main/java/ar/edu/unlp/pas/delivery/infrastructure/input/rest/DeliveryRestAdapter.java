package ar.edu.unlp.pas.delivery.infrastructure.input.rest;

import ar.edu.unlp.pas.delivery.application.ports.input.IDeliveryManager;
import ar.edu.unlp.pas.delivery.domain.model.Delivery;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.request.DeliveryCreateRequest;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryCreateResponse;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryQueryResponse;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.mapper.IDeliveryRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRestAdapter {

    private final IDeliveryManager deliveryManager;
   private final IDeliveryRestMapper deliveryRestMapper;
    @Autowired
    public DeliveryRestAdapter(IDeliveryManager deliveryManager, IDeliveryRestMapper deliveryRestMapper) {
        this.deliveryManager = deliveryManager;
        this.deliveryRestMapper = deliveryRestMapper;
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<DeliveryCreateResponse> createDelivery(@RequestBody @Valid DeliveryCreateRequest deliveryCreateRequest){
        Delivery delivery = deliveryRestMapper.toDelivery(deliveryCreateRequest);
        delivery = deliveryManager.createDelivery(delivery);
        return new ResponseEntity<>(deliveryRestMapper.toDeliveryCreateResponse(delivery), HttpStatus.CREATED);
    }

    @PostMapping("/take/{id}")
    public ResponseEntity<DeliveryQueryResponse> takeOrder(@PathVariable("id") Long deliveryId) {
        Delivery updatedDelivery = deliveryManager.takeOrder(deliveryId);
        DeliveryQueryResponse response = deliveryRestMapper.toDeliveryQueryResponse(updatedDelivery);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Void> candelOrder(@PathVariable("id") Long deliveryId) {
        deliveryManager.cancelOrder(deliveryId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deliver/{id}")
    public ResponseEntity<DeliveryQueryResponse> deliverOrder(@PathVariable("id") Long deliveryId) {
        Delivery updatedDelivery = deliveryManager.deliverOrder(deliveryId);
        DeliveryQueryResponse response = deliveryRestMapper.toDeliveryQueryResponse(updatedDelivery);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/pending")
    public ResponseEntity<List<DeliveryQueryResponse>> getAllPendingOrders(){
        List<Delivery> deliveries = deliveryManager.getPendingOrders();

        if (!deliveries.isEmpty()) {
            List<DeliveryQueryResponse> responseList = new ArrayList<>();
            for (Delivery delivery : deliveries) {
                DeliveryQueryResponse response = deliveryRestMapper.toDeliveryQueryResponse(delivery);
                responseList.add(response);
            }
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DeliveryQueryResponse>> getAllDelivery(){
        List<Delivery> deliveries = deliveryManager.getAll();

        if (!deliveries.isEmpty()) {
            List<DeliveryQueryResponse> responseList = new ArrayList<>();
            for (Delivery delivery : deliveries) {
                DeliveryQueryResponse response = deliveryRestMapper.toDeliveryQueryResponse(delivery);
                responseList.add(response);
            }
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
