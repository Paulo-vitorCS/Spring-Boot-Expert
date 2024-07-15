package br.com.spring_boot_expert.config;

import br.com.spring_boot_expert.rest.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/clients/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/products/**").hasRole("ADMIN")
                                .requestMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}

// https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter