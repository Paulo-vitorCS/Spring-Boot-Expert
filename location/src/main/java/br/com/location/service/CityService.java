package br.com.location.service;

import br.com.location.domain.City;
import br.com.location.repositories.CityRepository;
import br.com.location.repositories.specs.CitySpecs;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static br.com.location.repositories.specs.CitySpecs.idEqual;
import static br.com.location.repositories.specs.CitySpecs.inhabitantsGreaterThan;
import static br.com.location.repositories.specs.CitySpecs.nameEqual;
import static br.com.location.repositories.specs.CitySpecs.nameLike;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void listCities() {
        cityRepository.findAll().forEach(System.out::println);
    }

    public void listCitiesByName() {
        cityRepository.findByName("Ouvidor").forEach(System.out::println);
    }

    public void listCitiesByNameStartingWith() {
        cityRepository.findByNameStartingWith("Porto").forEach(System.out::println);
    }

    public void listCitiesByNameEndingWith() {
        cityRepository.findByNameEndingWith("a").forEach(System.out::println);
    }

    public void listCitiesByNameContaining() {
        cityRepository.findByNameContaining("a").forEach(System.out::println);
    }

    public void listCitiesByInhabitants() {
        cityRepository.findByInhabitants(80000L).forEach(System.out::println);
    }

    public void listCitiesByInhabitantsLessThan() {
        cityRepository.findByInhabitantsLessThan(100000L, Sort.by("inhabitants").descending()).forEach(System.out::println);
    }

    public void listCitiesByInhabitantsGreaterThan() {
        Pageable pageable = PageRequest.of(0, 3);
        cityRepository.findByInhabitantsGreaterThan(100000L, pageable).forEach(System.out::println);
    }

    public void listCitiesByInhabitantsLessThanAndNameLike() {
        cityRepository.findByInhabitantsLessThanAndNameLike(100000L, "O%").forEach(System.out::println);
    }

    public void listCitiesByNameLike() {
        cityRepository.findByNameLike("%za").forEach(System.out::println);
        cityRepository.findByNameLike("porto%").forEach(System.out::println);
    }

    public List<City> dinamicFilter(City city) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<City> example = Example.of(city, matcher);
        return cityRepository.findAll(example);
    }

    public void listCitiesByNameSpecs() {
        cityRepository.findAll(nameEqual("São Paulo").and(inhabitantsGreaterThan(1000000L)))
                .forEach(System.out::println);
    }

    public void listCitiesSpecsDinamicFilter(City city) {
        Specification<City> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (city.getId() != null) {
            specs = specs.and(idEqual(city.getId()));
        }

        if (StringUtils.hasText(city.getName())) {
            specs = specs.and(nameLike(city.getName()));
        }

        if (city.getInhabitants() != null) {
            specs = specs.and(inhabitantsGreaterThan(city.getInhabitants()));
        }

        cityRepository.findAll(specs).forEach(System.out::println);
    }

}
