package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.publication;

import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.domain.models.Publication;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication.PublicationCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication.PublicationUpdateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.PublicationCreateResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.PublicationQueryResponse;

public interface IPublicationRestMapper {
    Publication toPublication(PublicationCreateRequest publicationCreateRequest );

    PublicationQueryResponse toPublicationQueryResponse(Publication publication);
    PublicationCreateResponse toPublicationCreateResponse(Publication publication);
    //Publication toUpdatePublication(PublicationUpdateRequest publicationUpdateRequest);

}
