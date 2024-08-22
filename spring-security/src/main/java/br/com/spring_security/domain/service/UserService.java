package br.com.spring_security.domain.service;

import br.com.spring_security.domain.entity.Group;
import br.com.spring_security.domain.entity.User;
import br.com.spring_security.domain.entity.UserGroup;
import br.com.spring_security.domain.repositories.GroupRepository;
import br.com.spring_security.domain.repositories.UserGroupRepository;
import br.com.spring_security.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public User save(User user, List<String> groups) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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

    public User getUserWithPermissions(String login) {
        Optional<User> optionalUser = userRepository.findByLogin(login);
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();
        List<String> permissions = userGroupRepository.findPermissionsByUser(user);
        user.setPermissions(permissions);

        return user;
    }

}
