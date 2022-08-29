package br.com.almanak.almanakApi.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.repository.PlanoRepository;

// @Configuration
public class PlanoSeed implements CommandLineRunner{

    @Autowired
    PlanoRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(List.of( 
            new Plano("Gamer", null, 0d, EN_Booleano.sim, LocalDateTime.now()),
            new Plano("Pro Gamer", null, 2.99, EN_Booleano.sim, LocalDateTime.now()),
            new Plano("Elite", null, 9.99, EN_Booleano.sim, LocalDateTime.now())
        ));
    }
    
}
