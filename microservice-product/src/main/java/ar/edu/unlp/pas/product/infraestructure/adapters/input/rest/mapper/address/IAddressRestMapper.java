package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.address;

import ar.edu.unlp.pas.product.domain.models.Address;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.AddressCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.AddressQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductCreateResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductQueryResponse;

public interface IAddressRestMapper {
    Address toAddress(AddressCreateRequest addressCreateRequest);

    AddressCreateRequest toAddressCreateRequest(Address address);
    AddressQueryResponse toAddressQueryResponse(Address address);
}
