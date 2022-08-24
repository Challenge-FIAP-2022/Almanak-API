package br.com.almanak.almanakApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Contrato;
import br.com.almanak.almanakApi.service.ContratoService;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contService;

    @GetMapping
    public Page<Contrato> index(Pageable pageable){
        return contService.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contrato> show(@PathVariable Integer id){
        return ResponseEntity.of(contService.getById(id));
    }
}
