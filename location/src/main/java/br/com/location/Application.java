package br.com.location;

import br.com.location.domain.City;
import br.com.location.repositories.CityRepository;
import br.com.location.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CityService cityService;

	@Override
	public void run(String... args) throws Exception {
//		cityService.listCitiesByName();
//		cityService.listCitiesByInhabitants();
//		cityService.listCitiesByInhabitants();
//		cityService.listCitiesByNameStartingWith();
//		cityService.listCitiesByNameEndingWith();
//		cityService.listCitiesByNameContaining();
//		cityService.listCitiesByNameLike();
//		cityService.listCitiesByInhabitantsLessThan();
//		cityService.listCitiesByInhabitantsGreaterThan();
//		cityService.listCitiesByInhabitantsLessThanAndNameLike();
//		var city = new City(null, "porto Alegre", null);
//		cityService.dinamicFilter(city).forEach(System.out::println);
//		cityService.listCitiesByNameSpecs();
		var city = new City(null, "porto Alegre", null);
		cityService.listCitiesSpecsDinamicFilter(city);
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
