package ar.edu.unlp.pas.product.application.ports.input;

import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.domain.models.Publication;

import java.util.List;
public interface IProductManager {
    Product createProduct(Product product);
    Product modifyStock(Long id,Product product);
   
    List<Product> findProductsAll();
    List<Product> find(String name);
    Product findById(Long id);
    void deleteProduct(Long id);



}
