    package ar.edu.unlp.pas.product.domain.service;

    import ar.edu.unlp.pas.product.application.ports.output.IProductOutputPort;
    import org.springframework.beans.factory.annotation.Autowired;
    import java.util.List;

    import ar.edu.unlp.pas.product.application.ports.input.IProductManager;

    import ar.edu.unlp.pas.product.domain.models.Product;

    import org.springframework.stereotype.Service;

    @Service
    public class ProductService implements IProductManager {

        private final IProductOutputPort productOutputPort;
        @Autowired
        public ProductService(IProductOutputPort productOutputPort) {
            this.productOutputPort = productOutputPort;
        }



        @Override
        public Product createProduct(Product product) {
            product=productOutputPort.create(product);
            return product;
        }

        @Override
        public Product modifyStock(Long id, Product product) {
            return productOutputPort.update(id, product);
        }

        @Override
        public List<Product> findProductsAll() {
           List<Product> products;
           products=productOutputPort.findAll();
           return products;
        }

        @Override
        public List<Product> find(String name) {
            List<Product> products;
            products=productOutputPort.find(name);
            return products;
        }


        @Override
        public Product findById(Long id) {
            return productOutputPort.findById(id);
        }



        @Override
        public void deleteProduct(Long id) {
            productOutputPort.delete(id);
        }




    }