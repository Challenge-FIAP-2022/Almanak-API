package br.com.almanak.almanakApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Avaliacao;
import br.com.almanak.almanakApi.service.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {
    
    @Autowired
    private AvaliacaoService service;

    @GetMapping("jogo/{id}")
    public ResponseEntity<List<Avaliacao>> findById(@PathVariable Integer id){

        System.out.println(id);

        Optional<List<Avaliacao>> opt = service.listByGame(id);

        if(!opt.isEmpty())
            return ResponseEntity.of(opt);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
