package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.publication;

import ar.edu.unlp.pas.product.application.ports.input.IProductManager;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.domain.models.Publication;
import ar.edu.unlp.pas.product.domain.service.ProductService;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication.PublicationCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication.PublicationUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.AddressQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.PublicationCreateResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.PublicationQueryResponse;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicationRestMapperImpl implements IPublicationRestMapper {

    private final IProductManager productManager;

    public PublicationRestMapperImpl( IProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public Publication toPublication(PublicationCreateRequest publicationCreateRequest) {
        if (publicationCreateRequest == null) {
            return null;
        }

        // Busca el producto por su ID utilizando productService.findById
        Product product = productManager.findById(publicationCreateRequest.getProductId());

        Publication publication = new Publication();
        publication.setStock(publicationCreateRequest.getStock());
        publication.setSellPrice(publicationCreateRequest.getSellPrice());
        publication.setMinPrice(publicationCreateRequest.getMinPrice());
        publication.setMaxPrice(publicationCreateRequest.getMaxPrice());
        publication.setIsActive(publicationCreateRequest.getIsActive());
        publication.setProduct(product);
        return publication;
    }

    @Override
    public PublicationQueryResponse toPublicationQueryResponse(Publication publication) {
        if(publication==null){
            return null;
        }
        PublicationQueryResponse publicationQueryResponse=new PublicationQueryResponse();
        publicationQueryResponse.setPublicationId(publication.getId());
        publicationQueryResponse.setStock(publication.getStock());
        publicationQueryResponse.setSellPrice(publication.getSellPrice());
        publicationQueryResponse.setMinPrice(publication.getMinPrice());
        publicationQueryResponse.setMaxPrice(publication.getMaxPrice());
        publicationQueryResponse.setIsActive(publication.getIsActive());
        publicationQueryResponse.setProduct(publication.getProduct());
        return publicationQueryResponse;
    }

    @Override
    public PublicationCreateResponse toPublicationCreateResponse(Publication publication) {
        if(publication==null){
            return null;
        }
        PublicationCreateResponse publicationCreateResponse=new PublicationCreateResponse();
        publicationCreateResponse.setPublicationId(publication.getId());
        publicationCreateResponse.setStock(publication.getStock());
        publicationCreateResponse.setSellPrice(publication.getSellPrice());
        publicationCreateResponse.setMinPrice(publication.getMinPrice());
        publicationCreateResponse.setMaxPrice(publication.getMaxPrice());
        publicationCreateResponse.setIsActive(publication.getIsActive());
        publicationCreateResponse.setProduct(publication.getProduct());
        return publicationCreateResponse;

    }
/*
    @Override
    public Publication toUpdatePublication(PublicationUpdateRequest publicationUpdateRequest) {


        return null;
    }*/
}