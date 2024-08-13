package br.com.location.repositories.specs;

import br.com.location.domain.City;
import org.springframework.data.jpa.domain.Specification;

public abstract class CitySpecs {

    public static Specification<City> nameEqual(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<City> idEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<City> inhabitantsGreaterThan(Long value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("inhabitants"), value);
    }

    public static Specification<City> inhabitantsBetween(Long min, Long max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("inhabitants"), min, max);
    }

    public static Specification<City> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
                ("%" + name + "%").toUpperCase());
    }

}
