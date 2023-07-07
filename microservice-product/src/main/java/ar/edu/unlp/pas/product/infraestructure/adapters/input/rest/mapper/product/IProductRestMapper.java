package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.product;

import ar.edu.unlp.pas.product.domain.models.Address;
import ar.edu.unlp.pas.product.domain.models.Product;

import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.AddressCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductCreateResponse;

public interface IProductRestMapper {
    
    Product toProduct(ProductCreateRequest productCreaterequest);

    Product toUpdateProduct(ProductUpdateRequest productUpdateRequest);


    ProductCreateResponse toProductCreateResponse(Product product);
    ProductQueryResponse toProductQueryResponse(Product product);

}
