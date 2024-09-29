package com.mauriflores.mybooks.config;

import com.mauriflores.mybooks.filter.JwtReqFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {

    // Lazy, quita dependencias circulares
    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails userServiceAdmin = User.builder()
                .username("usrAdmin")
                .password("{noop}admin")
                .roles("Jefe")
                .build();

        UserDetails userServiceCategoria = User.builder()
                .username("usrCategoria")
                .password("{noop}test")
                .roles("Empleado","Jefe")
                .build();
        UserDetails userServiceLibro = User.builder()
                .username("usrLibro")
                .password("{noop}test")
                .roles("Empleado")
                .build();
        return new InMemoryUserDetailsManager(userServiceAdmin,userServiceCategoria,userServiceLibro);
    }
*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configure -> {
                    configure.requestMatchers(HttpMethod.GET,"/v1/libros").hasRole("EMPLEADO")
                            .requestMatchers(HttpMethod.GET,"/v1/libros/**").hasRole("EMPLEADO")
//                            .requestMatchers(HttpMethod.GET,"/v1/categorias/**").hasRole("EMPLEADO")
                            .requestMatchers(HttpMethod.POST,"/v1/libros").hasRole("JEFE")
                            .requestMatchers(HttpMethod.PUT,"/v1/libros").hasRole("JEFE")
                            .requestMatchers(HttpMethod.DELETE,"/v1/libros").hasRole("JEFE")
                            .requestMatchers(HttpMethod.POST,"/v1/categorias").hasRole("JEFE")
                            .requestMatchers(HttpMethod.PUT,"/v1/categorias").hasRole("JEFE")
                            .requestMatchers(HttpMethod.DELETE,"/v1/categorias").hasRole("JEFE")
                            .requestMatchers("/v1/categorias","/v1/authenticate","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll();
                }
        )
                .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf ->{ csrf.disable();});
        return http.build();
    }

    // JDBC Spring Security
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
