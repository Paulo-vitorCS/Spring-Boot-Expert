package br.com.spring_security.domain.repositories;

import br.com.spring_security.domain.entity.User;
import br.com.spring_security.domain.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, String> {

    @Query("""
            select distinct g.name
            from UserGroup ug 
            join ug.group g
            join ug.user u
            where u = ?1
            """)
    public List<String> findPermissionsByUser(User user);

}
