package br.com.almanak.almanakApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.repository.UsuarioRepository;

@Service
public class TokenService {
    
    @Autowired
    UsuarioRepository repository;

    // @Value("${almanak.jwt.secret}")
    // String secret;

    // public boolean validate(String token) {

    //     try{
    //         JWT.require(Algorithm.HMAC512("secret")).build().verify(token);
    //         return true;
    //     }catch(Exception e){
    //         return false;
    //     }

    // }

    // public Authentication getAuthenticationToken(String token) {
    //     String email = JWT.require(Algorithm.HMAC512("secret")).build().verify(token).getSubject();
        
    //     Optional<Usuario> optional = repository.findByEmail(email);
    //     if (optional.isEmpty()) return null;
    //     var user = optional.get();
    //     Authentication authentication = 
    //             new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        
    //     return authentication;
    // }
    
}
