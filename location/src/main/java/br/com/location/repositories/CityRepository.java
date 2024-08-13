package br.com.location.repositories;

import br.com.location.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByName(String name);
    List<City> findByNameStartingWith(String startingString);
    List<City> findByNameEndingWith(String endingString);
    List<City> findByNameContaining(String substring);

    @Query("select c from City c where lower(c.name) like lower(?1)")
    List<City> findByNameLike(String name);
    List<City> findByInhabitants(Long inhabitants);


}
