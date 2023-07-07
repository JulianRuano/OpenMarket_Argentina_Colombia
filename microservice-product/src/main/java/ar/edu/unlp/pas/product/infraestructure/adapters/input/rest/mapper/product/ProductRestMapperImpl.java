package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.product;

import ar.edu.unlp.pas.product.application.ports.input.IProductManager;
import ar.edu.unlp.pas.product.domain.models.Address;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.AddressCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.AddressQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductCreateResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.address.IAddressRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductRestMapperImpl implements IProductRestMapper, IAddressRestMapper {


    @Override
    public Product toProduct(ProductCreateRequest productCreateRequest) {
        if (productCreateRequest == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productCreateRequest.getName());
        product.setPrice(productCreateRequest.getPrice());
        product.setDescription(productCreateRequest.getDescription());
        product.setCategory(productCreateRequest.getCategory());
        product.setPersonId(productCreateRequest.getPersonId());

// Crear y establecer el objeto Address en el objeto Product
        AddressCreateRequest addressCreateRequest = productCreateRequest.getAddress();
        Address address = new Address();
        address.setHouseNumber(addressCreateRequest.getHouseNumber());
        address.setStreet(addressCreateRequest.getStreet());
        address.setCity(addressCreateRequest.getCity());
        address.setProvince(addressCreateRequest.getProvince());
        address.setCountry(addressCreateRequest.getCountry());

        product.setAddress(address);


        return product;


    }

    public Address toAddress(AddressCreateRequest addressCreateRequest) {
        if (addressCreateRequest == null) {
            return null;
        }
        Address address = new Address();
        address.setHouseNumber(addressCreateRequest.getHouseNumber());
        address.setStreet(addressCreateRequest.getStreet());
        address.setCity(addressCreateRequest.getCity());
        address.setProvince(addressCreateRequest.getProvince());
        address.setCountry(addressCreateRequest.getCountry());
        return address;
    }

    @Override
    public AddressCreateRequest toAddressCreateRequest(Address address) {
        if (address == null) {
            return null;
        }
        AddressCreateRequest addressCreateRequest = new AddressCreateRequest();
        addressCreateRequest.setHouseNumber(address.getHouseNumber());
        addressCreateRequest.setStreet(address.getStreet());
        addressCreateRequest.setCity(address.getCity());
        addressCreateRequest.setProvince(address.getProvince());
        addressCreateRequest.setCountry(address.getCountry());
        return addressCreateRequest;


    }

    @Override
    public AddressQueryResponse toAddressQueryResponse(Address address) {
        if (address == null) {
            return null;
        }
        AddressQueryResponse addressQueryResponse = new AddressQueryResponse();
        addressQueryResponse.setHouseNumber(address.getHouseNumber());
        addressQueryResponse.setStreet(address.getStreet());
        addressQueryResponse.setCity(address.getCity());
        addressQueryResponse.setProvince(address.getProvince());
        addressQueryResponse.setCountry(address.getCountry());
        addressQueryResponse.setAddressId(address.getId());
        return addressQueryResponse;
    }

    @Override
    public Product toUpdateProduct(ProductUpdateRequest productUpdateRequest) {
        if (productUpdateRequest == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productUpdateRequest.getName());
        product.setPrice(productUpdateRequest.getPrice());
        product.setDescription(productUpdateRequest.getDescription());
        product.setCategory(productUpdateRequest.getCategory());

        AddressCreateRequest addressCreateRequest=productUpdateRequest.getAddress();
        Address address=new Address();
        address.setHouseNumber(addressCreateRequest.getHouseNumber());
        address.setStreet(addressCreateRequest.getStreet());
        address.setCity(addressCreateRequest.getCity());
        address.setProvince(addressCreateRequest.getProvince());
        address.setCountry(addressCreateRequest.getCountry());
        product.setAddress(address);
        return product;
    }

    @Override
    public ProductCreateResponse toProductCreateResponse(Product product) {
        if (product == null) {
            return null;
        }
        ProductCreateResponse productCreateResponse = new ProductCreateResponse();
        productCreateResponse.setProductId(product.getId());
        productCreateResponse.setName(product.getName());
        productCreateResponse.setPrice(product.getPrice());
        productCreateResponse.setDescription(product.getDescription());
        productCreateResponse.setCategory(product.getCategory());
         AddressQueryResponse addressQueryResponse = toAddressQueryResponse(product.getAddress());
        productCreateResponse.setAddress(addressQueryResponse);


        productCreateResponse.setPersonId(product.getPersonId());
        return productCreateResponse;
    }

    @Override
    public ProductQueryResponse toProductQueryResponse(Product product) {
        if (product == null) {
            return null;
        }
        ProductQueryResponse productQueryResponse = new ProductQueryResponse();
        productQueryResponse.setProductId(product.getId());
        productQueryResponse.setName(product.getName());
        productQueryResponse.setPrice(product.getPrice());
        productQueryResponse.setDescription(product.getDescription());
        productQueryResponse.setAddress(toAddressQueryResponse(product.getAddress()));
        productQueryResponse.setPersonId(product.getPersonId());
        productQueryResponse.setCategory(product.getCategory());
        return productQueryResponse;

    }


}