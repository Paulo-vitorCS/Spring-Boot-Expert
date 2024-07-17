package br.com.spring_boot_expert.security.jwt;

import br.com.spring_boot_expert.rest.service.impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserServiceImpl userService;

    public JwtAuthFilter(JwtService jwtService, UserServiceImpl userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String auth = request.getHeader("Authorization");

        if (auth!= null && auth.startsWith("Bearer")) {
            String token = auth.split(" ")[1];
            boolean isTokenValid = jwtService.validToken(token);

            if (isTokenValid) {
                String userLogin = jwtService.getUserLogin(token);
                UserDetails userDetails = userService.loadUserByUsername(userLogin);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(request, response);

    }
}
