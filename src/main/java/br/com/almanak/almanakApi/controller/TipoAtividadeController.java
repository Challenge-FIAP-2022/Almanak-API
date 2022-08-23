package br.com.almanak.almanakApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.TipoAtividade;
import br.com.almanak.almanakApi.service.TipoAtividadeService;

@RestController
@RequestMapping("/api/tipoAtividade")
public class TipoAtividadeController {
    
    @Autowired
    private TipoAtividadeService tpService;

    @GetMapping
    public List<TipoAtividade> index(){
        return tpService.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoAtividade> show(@PathVariable Integer id){
        return ResponseEntity.of(tpService.getById(id));
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<TipoAtividade> findByName(@PathVariable String nome){
        return ResponseEntity.of(tpService.findByName(nome));
    }

    @PostMapping
    public ResponseEntity<TipoAtividade> create(@RequestBody @Valid TipoAtividade tipoAtividade){
        tpService.save(tipoAtividade);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tipoAtividade);
    }
    
}
