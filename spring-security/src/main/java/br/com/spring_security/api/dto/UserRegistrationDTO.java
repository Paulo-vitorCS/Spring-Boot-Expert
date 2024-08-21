package br.com.spring_security.api.dto;

import br.com.spring_security.domain.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserRegistrationDTO {

    private User user;
    private List<String> permissions;

}
