package br.com.almanak.almanakApi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Contrato;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.ContratoService;
import br.com.almanak.almanakApi.service.PlanoService;
import br.com.almanak.almanakApi.service.UsuarioService;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public Page<Contrato> index(Pageable pageable){
        return contService.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contrato> show(@PathVariable Integer id){
        return ResponseEntity.of(contService.getById(id));
    }

    @GetMapping("ativo/{id}")
    public ResponseEntity<Contrato> findByUser(@PathVariable Integer id){
        return ResponseEntity.of(contService.findByUser(id));
    }

    @PutMapping("contratacao/{nomePlano}")
    public ResponseEntity<Contrato> contratacao(@RequestBody @Valid Usuario usuarioBody, @PathVariable String nomePlano){
        var optionalPlano = planoService.findByName(nomePlano);

        if(!optionalPlano.isEmpty()){
            Plano plano = optionalPlano.get();
            var optional = usuarioService.getById(usuarioBody.getId());

            if(optional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            Usuario usuario = optional.get();
            Contrato contrato = new Contrato(usuario, plano, EN_Booleano.sim);
            contService.save(contrato);
            contrato.setUsuario(usuario.ajustar());

            return ResponseEntity.status(HttpStatus.CREATED).body(contrato);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Contrato> destroy(@PathVariable Integer id){
        var contratoOpt = contService.remove(id);
        if(contratoOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var contrato = contratoOpt.get();
            if(contrato.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(contrato);

        }
        
    }

}
