package br.com.location.repositories;

import br.com.location.domain.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    // ----- STRING VALUES -----
    List<City> findByName(String name);
    List<City> findByNameStartingWith(String startingString);
    List<City> findByNameEndingWith(String endingString);
    List<City> findByNameContaining(String substring);

    @Query("select c from City c where lower(c.name) like lower(?1)")
    List<City> findByNameLike(String name);

    // ----- NUMERICAL VALUES -----
    List<City> findByInhabitants(Long inhabitants);
    List<City> findByInhabitantsLessThan(Long inhabitants, Sort sort);
    List<City> findByInhabitantsLessThanEqual(Long inhabitants);  // <=
    List<City> findByInhabitantsGreaterThan(Long inhabitants, Pageable pageable); // Pageable -> limit
    List<City> findByInhabitantsGreaterThanEqual(Long inhabitants);  // >=
    List<City> findByInhabitantsLessThanAndNameLike(Long inhabitants, String nome);

}
