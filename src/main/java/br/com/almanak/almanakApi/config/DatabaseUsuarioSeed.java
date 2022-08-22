package br.com.almanak.almanakApi.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.repository.UsuarioRepository;

@Configuration
public class DatabaseUsuarioSeed implements CommandLineRunner {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(List.of( 
            new Usuario("AlmanaK", "almanak", "almanak", LocalDate.now(), LocalDateTime.now()),
            new Usuario("AlmanaK2", "almanak2", "almanak", LocalDate.now(), LocalDateTime.now()),
            new Usuario("AlmanaK3", "almanak3", "almanak", LocalDate.now(), LocalDateTime.now())
        ));
    }
    
}
