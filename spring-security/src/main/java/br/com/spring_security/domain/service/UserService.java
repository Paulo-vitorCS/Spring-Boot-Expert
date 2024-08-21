package br.com.spring_security.domain.service;

import br.com.spring_security.domain.entity.Group;
import br.com.spring_security.domain.entity.User;
import br.com.spring_security.domain.entity.UserGroup;
import br.com.spring_security.domain.repositories.GroupRepository;
import br.com.spring_security.domain.repositories.UserGroupRepository;
import br.com.spring_security.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    public User save(User user, List<String> groups) {
        userRepository.save(user);
        List<UserGroup> groupsList = groups.stream().map(groupName -> {
            Optional<Group> existingGroup = groupRepository.findByName(groupName);
            if (existingGroup.isPresent()) {
                Group group = existingGroup.get();
                return new UserGroup(user, group);
            }
            return null;
        })
                .filter(Objects::nonNull).toList();
        userGroupRepository.saveAll(groupsList);

        return user;
    }

}
