package br.com.almanak.almanakApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.service.PlanoService;

// @RestController
@RequestMapping("/api/plano")
public class PlanoController {

    @Autowired
    private PlanoService service;
 
    @GetMapping
    public List<Plano> index(){
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<Plano> create(@RequestBody @Valid Plano plano){
        service.save(plano);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(plano);
    }

    @PutMapping("{id}")
    public ResponseEntity<Plano> update(@PathVariable Integer id, @RequestBody @Valid Plano newPlano){
        var optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var plano = optional.get();
        BeanUtils.copyProperties(newPlano, plano);
        plano.setId(id);

        service.save(plano);
        return ResponseEntity.ok(plano);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Integer id){
        var optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
