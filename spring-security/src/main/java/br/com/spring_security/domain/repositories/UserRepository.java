package br.com.spring_security.domain.repositories;


import br.com.spring_security.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
