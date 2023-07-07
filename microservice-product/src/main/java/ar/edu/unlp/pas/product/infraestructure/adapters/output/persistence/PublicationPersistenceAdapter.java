package ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence;

import ar.edu.unlp.pas.product.application.ports.input.IProductManager;
import ar.edu.unlp.pas.product.application.ports.output.IPublicationOutputPort;
import ar.edu.unlp.pas.product.domain.exception.ProductNotFoundException;
import ar.edu.unlp.pas.product.domain.exception.PublicationNotFoundException;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.domain.models.Publication;
import ar.edu.unlp.pas.product.infraestructure.adapters.PublicationSpecification;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.AddressEntity;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.ProductEntity;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.PublicationEntity;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.mapper.IPublicationPersistenceMapper;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.repository.IPublicationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
public class PublicationPersistenceAdapter implements IPublicationOutputPort {
    @Autowired
    private final IPublicationRepository publicationRepository;
    @Autowired
    private final IPublicationPersistenceMapper publicationPersistenceMapper;
    @Autowired
    private final IProductManager productManager;

    @Override
    public Publication create(Publication publication) {
        Product product = productManager.findById(publication.getProduct().getId());
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setCategory(product.getCategory());
        productEntity.setAddress(new AddressEntity());
        productEntity.getAddress().setStreet(product.getAddress().getStreet());
        productEntity.getAddress().setHouseNumber(product.getAddress().getHouseNumber());
        productEntity.getAddress().setCity(product.getAddress().getCity());
        productEntity.getAddress().setProvince(product.getAddress().getProvince());
        productEntity.getAddress().setCountry(product.getAddress().getCountry());

        PublicationEntity publicationEntity = publicationPersistenceMapper.toPublicationEntity(publication);
        publicationEntity.setProduct(productEntity);

        PublicationEntity savedPublicationEntity = publicationRepository.save(publicationEntity);
        return publicationPersistenceMapper.toPublication(savedPublicationEntity);
    }



    @Override
    public void suspendPublication(Long publicationId) {
        Optional<PublicationEntity> optionalPublication = publicationRepository.findById(publicationId);

        if (optionalPublication.isPresent()) {
            PublicationEntity publicationEntity = optionalPublication.get();
            publicationEntity.setIsActive(false);
            publicationRepository.save(publicationEntity);
        } else {
            throw new PublicationNotFoundException("Publication with id " + publicationId + " not found");
        }
    }

    @Override
    public Publication findById(Long idPublication) {
        Optional<PublicationEntity> optionalPublicationEntity = publicationRepository.findById(idPublication);
        return optionalPublicationEntity.map(publicationPersistenceMapper::toPublication).orElseThrow(() -> new ProductNotFoundException("Publication with id " + idPublication+ " not found"));
    }

    @Override
    public List<Publication> findAll() {
        List<PublicationEntity> publicationEntities = publicationRepository.findAll();
        return publicationEntities.stream().map(publicationPersistenceMapper::toPublication).collect(Collectors.toList());
    }

    @Override
    public Publication decreaseStock(Long idPublication, Integer newStock) {
        Optional<PublicationEntity> optionalPublication = publicationRepository.findById(idPublication);

        if (optionalPublication.isPresent()) {
            PublicationEntity publicationEntity = optionalPublication.get();
            publicationEntity.setStock(newStock);
            publicationRepository.save(publicationEntity);
            return publicationPersistenceMapper.toPublication(publicationEntity);
        } else {
            throw new PublicationNotFoundException("Publication with id " + idPublication + " not found");
        }
    }

    @Override
    public List<Publication> filterPublications(String name, String description, Double minPrice, Double maxPrice, String category, String country) {
        Specification<PublicationEntity> spec = Specification.where(PublicationSpecification.productNameContains(name))
                .and(PublicationSpecification.productDescriptionContains(description))
                .and(PublicationSpecification.productPriceBetween(minPrice, maxPrice))
                .and(PublicationSpecification.productCategoryIs(category))
                .and(PublicationSpecification.addressCountryIs(country));
        List<PublicationEntity> publicationEntities = publicationRepository.findAll(spec);

        // Asegúrate de convertir la lista de entidades a tu modelo de dominio (Publication)
        List<Publication> publications = publicationEntities.stream()
                .map(publicationPersistenceMapper::toPublication)
                .collect(Collectors.toList());

        return publications;
    }


}
