package ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.mapper;

import ar.edu.unlp.pas.product.domain.models.Publication;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPublicationPersistenceMapper {
    PublicationEntity toPublicationEntity(Publication publication);
    Publication toPublication(PublicationEntity publicationEntity);
}
