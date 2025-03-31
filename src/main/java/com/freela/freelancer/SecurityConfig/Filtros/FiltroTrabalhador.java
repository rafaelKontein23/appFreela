package com.freela.freelancer.SecurityConfig.Filtros;

import com.freela.freelancer.Provider.JWTProviderTrabalhador;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FiltroTrabalhador  extends OncePerRequestFilter {

    @Autowired
    JWTProviderTrabalhador jwtProviderTrabalhador;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : "";
        List<String> rotasPublicas = List.of("/trabalhador/login", "/trabalhador/cadastra","/cadastrar/profissao", "/trabalhador/retorna/profissoes" );

        if (rotasPublicas.stream().anyMatch(request.getRequestURI()::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getRequestURI().startsWith("/trabalhador") || request.getRequestURI().startsWith("/feed")){
            var token = this.jwtProviderTrabalhador.validarToken(header);
            if(token == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            var roles = token.getClaim("roles").asList(Object.class);
            request.setAttribute("id", token.getSubject());
            var grands = roles.stream().map(
                    role-> new SimpleGrantedAuthority("ROLE_" + role.toString())
            ).toList();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null,grands);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
