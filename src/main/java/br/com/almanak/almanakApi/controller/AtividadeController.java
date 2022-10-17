package br.com.almanak.almanakApi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Atividade;
import br.com.almanak.almanakApi.model.TipoAtividade;
import br.com.almanak.almanakApi.service.AtividadeService;
import br.com.almanak.almanakApi.service.TipoAtividadeService;
import br.com.almanak.almanakApi.service.UsuarioService;

@RestController
@RequestMapping("/api/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    @Autowired
    private TipoAtividadeService tpAtividadeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<Atividade> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Atividade> show(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @PostMapping("duvida/{id}")
    public ResponseEntity<Atividade> duvida(@PathVariable Integer id, @RequestBody String jsonString){

        var optUsuario = usuarioService.getById(id);
        Optional<TipoAtividade>  optTipoAtividade= tpAtividadeService.getById(11);

        if(!optUsuario.isEmpty() && !optTipoAtividade.isEmpty()){

            Atividade atividade = new Atividade();

            atividade.addUsuario(optUsuario.get());
            atividade.addTipoAtividade(optTipoAtividade.get());
            atividade.setDesc(jsonString);

            service.save(atividade);
        
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(atividade);

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
