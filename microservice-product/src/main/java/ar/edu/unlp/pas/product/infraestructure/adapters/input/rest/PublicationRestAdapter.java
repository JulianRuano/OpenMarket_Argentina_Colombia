package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest;

import ar.edu.unlp.pas.product.application.ports.input.IPublicationManager;
import ar.edu.unlp.pas.product.domain.exception.InsufficientStockException;
import ar.edu.unlp.pas.product.domain.exception.InvalidQuantityException;
import ar.edu.unlp.pas.product.domain.exception.ProductNotFoundException;
import ar.edu.unlp.pas.product.domain.exception.PublicationNotFoundException;
import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.domain.models.Publication;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication.PublicationCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.ProductQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.PublicationCreateResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response.PublicationQueryResponse;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.mapper.publication.IPublicationRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publications")
public class PublicationRestAdapter {
    private final IPublicationManager publicationManager;

    private final IPublicationRestMapper publicationRestMapper;

    @Autowired
    public PublicationRestAdapter(IPublicationManager publicationManager, IPublicationRestMapper publicationRestMapper){
        this.publicationManager = publicationManager;
        this.publicationRestMapper = publicationRestMapper;
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<PublicationCreateResponse> createPublication(@RequestBody @Valid PublicationCreateRequest publicationCreateRequest){
        // Request to domain
        Publication publication = publicationRestMapper.toPublication(publicationCreateRequest);

        publication = publicationManager.createPublication(publication);


        return new ResponseEntity<>(publicationRestMapper.toPublicationCreateResponse(publication), HttpStatus.CREATED);
    }

    @PostMapping("/suspend/{id}")
    @ResponseBody
    public ResponseEntity<?> suspendPublication( @PathVariable Long id ) {
      try{
           publicationManager.suspendPublication(id);
            return ResponseEntity.ok().build();
      }catch (PublicationNotFoundException ex){
          Map<String, String> errorResponse = new HashMap<>();
          errorResponse.put("errorMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

      }
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getPublicationById(@PathVariable("id") Long id) {
        try {
            Publication publication= publicationManager.findById(id);
            PublicationQueryResponse publicationQueryResponse = publicationRestMapper.toPublicationQueryResponse(publication);
            return ResponseEntity.ok(publicationQueryResponse);
        } catch (ProductNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(publicationManager.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PublicationQueryResponse>> filterPublications(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "country", required = false) String country)
    {

        List<Publication> publications = publicationManager.filterPublications(name, description, minPrice, maxPrice, category,country);

        if (publications.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PublicationQueryResponse> publicationQueryResponses = publications.stream()
                .map(publicationRestMapper::toPublicationQueryResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(publicationQueryResponses);
    }

    @PostMapping("/decreaseStock")
    @ResponseBody
    public ResponseEntity<?> decreaseStock( @RequestParam("id") Long id, @RequestParam("quantity") int quantity) {
        try {
            Publication publication = publicationManager.decreaseStock(id, quantity);
            PublicationQueryResponse publicationQueryResponse = publicationRestMapper.toPublicationQueryResponse(publication);
            return ResponseEntity.ok(publicationQueryResponse);
        } catch (ProductNotFoundException | InsufficientStockException | InvalidQuantityException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
