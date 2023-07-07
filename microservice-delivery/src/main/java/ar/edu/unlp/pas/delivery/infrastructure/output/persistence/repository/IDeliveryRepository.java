package ar.edu.unlp.pas.delivery.infrastructure.output.persistence.repository;

import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeliveryRepository extends JpaRepository<DeliveryEntity,Long> {
    List<DeliveryEntity> findByDeliveryStatus(DeliveryStatus status);

}
