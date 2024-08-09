package com.readerspath.backend.filter;

import com.readerspath.backend.service.UserAuthService;
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

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String email = request.getHeader("email");
        if (email != null) {
            UserDetails user = userAuthService.loadUserByUsername(email); // DB call
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

//        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            try {
//                String token = authHeader.substring(7);
//                String email = jwtTokenService.getUsernameFromToken(token);
//                UserDetails user = userAuthService.loadUserByUsername(email); // DB call
//                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(auth);
//
//                filterChain.doFilter(request, response);
//            } catch (Exception ex) {
//                log.error("error: %s".formatted(ex.getMessage()));
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), Map.of("error", ex.getMessage()));
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }
    }
}
