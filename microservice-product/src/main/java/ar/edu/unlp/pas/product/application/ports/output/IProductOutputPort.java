package ar.edu.unlp.pas.product.application.ports.output;

import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IProductOutputPort {
     Product create(Product product);
     Product update(Long id,Product product);
     List<Product> find(String name);
     List<Product> findAll();
     Product findById(Long id);
     void delete(Long id);


}
