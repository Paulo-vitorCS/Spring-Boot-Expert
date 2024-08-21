package br.com.spring_security.domain.repositories;

import br.com.spring_security.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {

    Optional<Group> findByName(String name);

}
