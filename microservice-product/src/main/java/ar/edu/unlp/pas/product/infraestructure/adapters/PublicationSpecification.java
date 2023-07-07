package ar.edu.unlp.pas.product.infraestructure.adapters;

import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.PublicationEntity;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class PublicationSpecification {

    public static Specification<PublicationEntity> productNameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> product = root.join("product");
            return criteriaBuilder.like(criteriaBuilder.lower(product.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<PublicationEntity> productDescriptionContains(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> product = root.join("product");
            return criteriaBuilder.like(criteriaBuilder.lower(product.get("description")), "%" + description.toLowerCase() + "%");
        };
    }

    public static Specification<PublicationEntity> productPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (minPrice != null && minPrice > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("sellPrice"), minPrice));
            }

            if (maxPrice != null && maxPrice > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("sellPrice"), maxPrice));
            }

            return predicate;
        };
    }

    public static Specification<PublicationEntity> productCategoryIs(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> product = root.join("product");
            return criteriaBuilder.equal(criteriaBuilder.lower(product.get("category")), category.toLowerCase());
        };
    }

    public static Specification<PublicationEntity> addressCountryIs(String country) {
        return (root, query, criteriaBuilder) -> {
            if (country == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> product = root.join("product");
            Join<Object, Object> address = product.join("address");
            return criteriaBuilder.equal(criteriaBuilder.lower(address.get("country")), country.toLowerCase());
        };
    }

}