package br.com.spring_boot_expert;

import br.com.spring_boot_expert.domain.Client;
import br.com.spring_boot_expert.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClientRepository clientRepository) {
		return args-> {
			Client c = new Client("Paulo Vitor");
			clientRepository.save(c);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
