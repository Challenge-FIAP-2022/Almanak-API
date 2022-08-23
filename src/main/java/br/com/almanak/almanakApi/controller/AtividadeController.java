package br.com.almanak.almanakApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Atividade;
import br.com.almanak.almanakApi.service.AtividadeService;

@RestController
@RequestMapping("/api/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService tpService;

    @GetMapping
    public List<Atividade> index(){
        return tpService.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Atividade> show(@PathVariable Integer id){
        return ResponseEntity.of(tpService.getById(id));
    }
    
}
