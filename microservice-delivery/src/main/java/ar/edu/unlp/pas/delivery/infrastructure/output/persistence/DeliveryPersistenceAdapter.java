package ar.edu.unlp.pas.delivery.infrastructure.output.persistence;

import ar.edu.unlp.pas.delivery.application.ports.output.IDeliveryOutputPort;
import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import ar.edu.unlp.pas.delivery.domain.exception.DeliveryNotFound;
import ar.edu.unlp.pas.delivery.domain.model.Delivery;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity.DeliveryEntity;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.mapper.IDeliveryPersistenceMapper;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.repository.IDeliveryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
public class DeliveryPersistenceAdapter implements IDeliveryOutputPort {
    @Autowired
    private final IDeliveryRepository deliveryRepository;
    @Autowired
    private final IDeliveryPersistenceMapper deliveryPersistenceMapper;

    @Override
    public Delivery createDelivery(Delivery delivery) {
        DeliveryEntity deliveryEntity = deliveryPersistenceMapper.toDeliveryEntity(delivery);
        DeliveryEntity savedDeliveryEntity = deliveryRepository.save(deliveryEntity);
        return deliveryPersistenceMapper.toDelivery(savedDeliveryEntity);
    }

    @Override
    public List<Delivery> getPerdingOrders() {
        List<DeliveryEntity> deliveryEntities = deliveryRepository.findByDeliveryStatus(DeliveryStatus.STATUS_PENDING);
        return deliveryEntities.stream().map(deliveryPersistenceMapper::toDelivery).collect(Collectors.toList());

    }

    @Override
    public Delivery takeOrder(Long idDelivery) {
        Optional<DeliveryEntity> optionalDeliveryEntity = deliveryRepository.findById(idDelivery);

        if (optionalDeliveryEntity.isEmpty()) {
            throw new DeliveryNotFound("Delivery with id " + idDelivery + " not found");
        }

        DeliveryEntity deliveryEntity = optionalDeliveryEntity.get();

        // Actualiza el estado de la entidad a STATUS_PICKEDUP
        deliveryEntity.setDeliveryStatus(DeliveryStatus.STATUS_PICKEDUP);


        // Guarda la entidad actualizada en el repositorio
        DeliveryEntity updatedDeliveryEntity = deliveryRepository.save(deliveryEntity);

        // Convierte la entidad guardada en un objeto de dominio Delivery y devuélvela
        return deliveryPersistenceMapper.toDelivery(updatedDeliveryEntity);
    }

    @Override
    public Delivery deliverOrder(Long idDelivery) {
        Optional<DeliveryEntity> optionalDeliveryEntity = deliveryRepository.findById(idDelivery);

        if (optionalDeliveryEntity.isEmpty()) {
            throw new DeliveryNotFound("Delivery with id " + idDelivery + " not found");
        }

        DeliveryEntity deliveryEntity = optionalDeliveryEntity.get();

        // Actualiza el estado de la entidad a STATUS_DELIVERED
        deliveryEntity.setDeliveryStatus(DeliveryStatus.STATUS_DELIVERED);

        // Guarda la entidad actualizada en el repositorio
        DeliveryEntity updatedDeliveryEntity = deliveryRepository.save(deliveryEntity);

        // Convierte la entidad guardada en un objeto de dominio Delivery y devuélvela
        return deliveryPersistenceMapper.toDelivery(updatedDeliveryEntity);
    }

    @Override
    public void cancelOrder(Long idDelivery) {
        Optional<DeliveryEntity> optionalDeliveryEntity = deliveryRepository.findById(idDelivery);

        if (optionalDeliveryEntity.isEmpty()) {
            throw new DeliveryNotFound("Delivery with id " + idDelivery + " not found");
        }

        DeliveryEntity deliveryEntity = optionalDeliveryEntity.get();

        // Actualiza el estado de la entidad a STATUS_DELIVERED
        deliveryEntity.setDeliveryStatus(DeliveryStatus.STATUS_CANCELLED);

        // Guarda la entidad actualizada en el repositorio
        deliveryRepository.save(deliveryEntity);

    }

    @Override
    public List<Delivery> getAll() {
       List<DeliveryEntity> deliveryEntities=deliveryRepository.findAll();
       return deliveryEntities.stream().map(deliveryPersistenceMapper::toDelivery).collect(Collectors.toList());
    }
}
