package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationUpdateRequest {
    @NotNull(message = "active may not be empty")
    private boolean isActive;
}
