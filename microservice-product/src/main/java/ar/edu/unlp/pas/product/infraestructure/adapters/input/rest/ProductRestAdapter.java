package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest;


import java.util.*;

import javax.validation.Valid;

import ar.edu.unlp.pas.product.application.ports.input.IProductManager;
import ar.edu.unlp.pas.product.domain.exception.ProductNotFoundException;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductCreateResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.product.IProductRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/products")
public class ProductRestAdapter {
    private final IProductManager productManager;
    private final IProductRestMapper productRestMapper;

    @Autowired
    public ProductRestAdapter(IProductManager productManager, IProductRestMapper productRestMapper){
        this.productManager = productManager;
        this.productRestMapper = productRestMapper;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductCreateResponse> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest){
        // Request to domain
        Product product = productRestMapper.toProduct(productCreateRequest);

        product = productManager.createProduct(product);

        
        return new ResponseEntity<>(productRestMapper.toProductCreateResponse(product), HttpStatus.CREATED);
    }


   @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productManager.findById(id);
            ProductQueryResponse productQueryResponse = productRestMapper.toProductQueryResponse(product);
            return ResponseEntity.ok(productQueryResponse);
        } catch (ProductNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductQueryResponse>> getAllProducts(){
        List<Product> products = productManager.findProductsAll();

        if (!products.isEmpty()) {
            List<ProductQueryResponse> responseList = new ArrayList<>();
            for (Product product : products) {
                ProductQueryResponse response = productRestMapper.toProductQueryResponse(product);
                responseList.add(response);
            }
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductQueryResponse> updateProduct(@RequestBody @Valid ProductUpdateRequest productUpdateRequest, @PathVariable Long id ) {
       Product product = productRestMapper.toUpdateProduct(productUpdateRequest);
       product = productManager.modifyStock(id,product);

        if (product != null) {
            return ResponseEntity.ok(productRestMapper.toProductQueryResponse(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            productManager.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



}



