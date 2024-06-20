package br.com.spring_boot_expert.repositories;

import br.com.spring_boot_expert.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
