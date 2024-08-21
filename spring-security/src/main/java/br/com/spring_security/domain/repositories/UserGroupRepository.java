package br.com.spring_security.domain.repositories;

import br.com.spring_security.domain.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, String> {
}
