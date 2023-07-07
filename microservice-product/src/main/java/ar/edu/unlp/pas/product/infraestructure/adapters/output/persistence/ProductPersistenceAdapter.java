package ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence;
import ar.edu.unlp.pas.product.application.ports.output.IProductOutputPort;
import ar.edu.unlp.pas.product.domain.exception.ProductNotFoundException;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.PublicationSpecification;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.AddressEntity;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.ProductEntity;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.mapper.IProductPersistenceMapper;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.repository.IProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Data
public class ProductPersistenceAdapter implements IProductOutputPort {
    @Autowired
    private final IProductRepository productRepository;
    @Autowired
    private final IProductPersistenceMapper productPersistenceMapper;

    @Override
    public Product create(Product product) {
        ProductEntity productEntity = productPersistenceMapper.toProductEntity(product);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productPersistenceMapper.toProduct(savedProductEntity);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();

            productEntity.setName(product.getName());
            productEntity.setPrice(product.getPrice());
            productEntity.setDescription(product.getDescription());
            productEntity.setCategory(product.getCategory());
            AddressEntity addressEntity=new AddressEntity();
            addressEntity.setId(optionalProductEntity.get().getAddress().getId());
            addressEntity.setStreet(product.getAddress().getStreet());
            addressEntity.setCity(product.getAddress().getCity());
            addressEntity.setProvince(product.getAddress().getProvince());
            addressEntity.setHouseNumber(product.getAddress().getHouseNumber());
            addressEntity.setCountry(product.getAddress().getCountry());
            productEntity.setAddress(addressEntity);



            ProductEntity updatedProductEntity = productRepository.save(productEntity);
            return productPersistenceMapper.toProduct(updatedProductEntity);
        }
        return null;
    }

    @Override
    public List<Product> find(String name) {
        List<ProductEntity> productEntities = productRepository.findByName(name);
        return productEntities.stream().map(productPersistenceMapper::toProduct).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productPersistenceMapper::toProduct).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        return optionalProductEntity.map(productPersistenceMapper::toProduct).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if (optionalProductEntity.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }




}