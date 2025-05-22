package com.project.humanresource.config;

import com.project.humanresource.dto.JwtUserData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private JwtUserDetails jwtUserDetails;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return List.of(
                "/api/users/login",
                "/api/users/register",
                "/api/users/activate",
                "/api/auth/verify-email"
        ).stream().anyMatch(path::startsWith);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeaderAuthorization =request.getHeader("Authorization");
        System.out.println("header .... : " +requestHeaderAuthorization);

        if (Objects.nonNull(requestHeaderAuthorization) && requestHeaderAuthorization.startsWith("Bearer ")) {
            String token=requestHeaderAuthorization.substring(7);
            Optional<JwtUserData> userDataOpt=jwtManager.validateToken(token);
            if (userDataOpt.isPresent()) {
                JwtUserData userData=userDataOpt.get();

                UserDetails userDetails=jwtUserDetails.loadUserById(userData.userId());
                UsernamePasswordAuthenticationToken springToken=new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(springToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
