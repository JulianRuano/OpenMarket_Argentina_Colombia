package ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.repository;

import ar.edu.unlp.pas.product.infraestructure.adapters.PublicationSpecification;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.ProductEntity;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.PublicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicationRepository extends JpaRepository<PublicationEntity,Long> , JpaSpecificationExecutor<PublicationEntity> {

}
