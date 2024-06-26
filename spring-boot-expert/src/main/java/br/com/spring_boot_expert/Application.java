package br.com.spring_boot_expert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired ClientRepository clientRepository) {
//		return args-> {
//			Client c = new Client("Paulo Vitor");
//			clientRepository.save(c);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
