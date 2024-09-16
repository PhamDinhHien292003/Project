package com.example.vgc_project.security;


import ch.qos.logback.core.util.StringUtil;
import com.example.vgc_project.jwt.UserJwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Autowired
    UserJwt userJwt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        if (StringUtils.hasText(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try{
                if(userJwt.isTrueToken(jwtToken)){
                    Claims claims = userJwt.getAllClaimsFromToken(jwtToken);

                    Object scope = claims.get("scope");

                    String[] words = scope.toString().split(" ");


                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken("A", "B", AuthorityUtils.createAuthorityList(List.of(words)));
                    SecurityContext securityContext  = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(usernamePasswordAuthenticationToken);
                }
                else {
                    logger.error("DOESNT MATCH");
                }
            }
            catch (Exception e){
                logger.info(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
