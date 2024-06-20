package br.com.spring_boot_expert.repositories;

import br.com.spring_boot_expert.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
