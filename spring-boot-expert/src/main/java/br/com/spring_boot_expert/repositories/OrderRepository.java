package br.com.spring_boot_expert.repositories;

import br.com.spring_boot_expert.domain.Client;
import br.com.spring_boot_expert.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

    @Query("select o from Order o left join fetch o.items where o.id = :id")
    Optional<Order> findByIdFetchItems(Integer id);

}
