package br.com.location.repositories;

import br.com.location.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByName(String name);
    List<City> findByNameStartingWith(String startingString);
    List<City> findByNameEndingWith(String endingString);
    List<City> findByNameContaining(String substring);
    List<City> findByInhabitants(Long inhabitants);


}
