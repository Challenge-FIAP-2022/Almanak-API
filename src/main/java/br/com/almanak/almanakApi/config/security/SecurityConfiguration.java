package br.com.almanak.almanakApi.config.security;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests() 
 
                // Usuários
                .antMatchers(HttpMethod.GET, "/api/usuario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/usuario/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/usuario/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/usuario/**").authenticated()
                
                // web
                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //.and()
                .headers().frameOptions().disable()
            .and()
                .formLogin()
                //.loginPage("/meulogin")
                //.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    
}
