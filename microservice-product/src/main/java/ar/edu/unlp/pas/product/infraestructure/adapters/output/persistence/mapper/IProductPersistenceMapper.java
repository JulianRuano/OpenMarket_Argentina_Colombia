package ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.mapper;

import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductPersistenceMapper {
    ProductEntity toProductEntity(Product product);
    Product toProduct(ProductEntity productEntity);

}
