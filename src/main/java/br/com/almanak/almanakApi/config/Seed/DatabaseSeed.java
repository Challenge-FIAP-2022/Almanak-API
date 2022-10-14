package br.com.almanak.almanakApi.config.Seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.almanak.almanakApi.repository.UsuarioRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     usuarioRepository.save(new Usuario("Almanak", 
    //                                         "email@email.com.br", 
    //                                         passwordEncoder.encode("123456"), 
    //                                         new Role("ROLE_ADMIN")));

    //     usuarioRepository.save(new Usuario("Pedro", 
    //                                         "pedro@email.com.br", 
    //                                         passwordEncoder.encode("123456"), 
    //                                         new Role("ROLE_USER")));
        
    // }
    
}
