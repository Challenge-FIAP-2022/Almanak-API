package br.com.almanak.almanakApi.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.almanak.almanakApi.model.Role;
import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.repository.UsuarioRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        usuarioRepository.save(new Usuario("Almanak", "email@email.com.br", passwordEncoder.encode("123456"), LocalDate.now()));


        usuarioRepository.save(new Usuario("Pedro", "pedro@email.com.br", passwordEncoder.encode("123456"), LocalDate.now()));
        
    }
    
}
