package br.com.spring_boot_expert.repositories;

import br.com.spring_boot_expert.domain.Client;
import br.com.spring_boot_expert.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

}
