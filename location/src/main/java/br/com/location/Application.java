package br.com.location;

import br.com.location.domain.City;
import br.com.location.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public void run(String... args) throws Exception {
//		listCitiesByName();
//		listCitiesByInhabitants();
//		listCitiesByInhabitants();
//		listCitiesByNameStartingWith();
//		listCitiesByNameEndingWith();
//		listCitiesByNameContaining();
	}

	void listCities() {
		cityRepository.findAll().forEach(System.out::println);
	}

	void listCitiesByName() {
		cityRepository.findByName("Ouvidor").forEach(System.out::println);
	}

	void listCitiesByNameStartingWith() {
		cityRepository.findByNameStartingWith("Porto").forEach(System.out::println);
	}

	void listCitiesByNameEndingWith() {
		cityRepository.findByNameEndingWith("a").forEach(System.out::println);
	}

	void listCitiesByNameContaining() {
		cityRepository.findByNameContaining("a").forEach(System.out::println);
	}

	void listCitiesByInhabitants() {
		cityRepository.findByInhabitants(80000L).forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
