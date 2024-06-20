package br.com.spring_boot_expert.repositories;

import br.com.spring_boot_expert.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    boolean existsByName(String name);

    @Query(value = "select * from client c where c.name like '%:name%'", nativeQuery = true)
    List<Client> buscaPorNome( @Param("name") String name);

    @Query("select c from Client c left join fetch c.orders where c.id = :id")
    Client findClientFetchOrders(@Param("id") Integer id);

}
