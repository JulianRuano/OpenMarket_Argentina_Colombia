package ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="publications")
@Data
public class PublicationEntity {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock")
    private Integer stock;

    @Column(name="sell_price")
    private Double sellPrice;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "max_price")
    private Double maxPrice;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
}
