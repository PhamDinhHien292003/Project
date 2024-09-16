package com.example.vgc_project.security;

import com.example.vgc_project.Enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.authorization.method.AuthorizationAdvisorProxyFactory.withDefaults;

@Configuration
@EnableWebSecurity
public class filter {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    CustomJwtFilter customJwtFilter;


     String[] privateLink = new String[]{
            "Users/getAllUser" , "Users/get" , "Users/getAllUser" , "Users/del_with_id" , "cinema/**",
            "ticket/get"
    };
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
                    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedHeaders(List.of("*"));
                    return configuration;
                })).csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeRequests().
                requestMatchers("/Users/login" , "/films/files/**" , "/films/all_films"
                        , "/films/getShowingList" , "films/film" ,"Users/sign_up" , "foods/**" ,
                        "/ticket/buy_ticket"
                ).permitAll().
                requestMatchers(privateLink)
                .hasAnyAuthority(Role.ADMIN.name(),Role.MANAGEMENT.name() ).
                anyRequest().authenticated().and().httpBasic(Customizer.withDefaults());
        http.addFilterBefore(customJwtFilter  , UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
