package br.com.almanak.almanakApi.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.almanak.almanakApi.Interface.PlanoDTO;
import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.service.PlanoService;

@RestController
@RequestMapping("/api/plano")
public class PlanoController {

    @Autowired
    private PlanoService service;
 
    @GetMapping
    public List<Plano> index(){
        return service.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Plano> show(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("valido/{flag}")
    public ResponseEntity<List<PlanoDTO>> listByValid(@PathVariable() EN_Booleano flag){
        Optional<List<Plano>> opts = service.listByValid(flag);

        if(!opts.isEmpty()){
            List<Plano> planos = opts.get();
            List<PlanoDTO> dtos = new PlanoDTO().convertList(planos);
            return ResponseEntity.ok(dtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        
    @GetMapping("nome/{nome}")
    public ResponseEntity<PlanoDTO> findByName(@PathVariable String nome){
        Optional<Plano> plano = service.findByName(nome);
        
        if(!plano.isEmpty())
            return ResponseEntity.ok(new PlanoDTO().convert(plano.get()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Plano> create(@RequestBody @Valid Plano plano){
        var optionalName = service.findByName(plano.getName());
        if(optionalName.isEmpty()){
            service.save(plano);
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(plano);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

    @DeleteMapping("{nome}")
    public ResponseEntity<Plano> destroy(@PathVariable String nome){
        var planoResponse = service.remove(nome);
        if(planoResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var plano = planoResponse.get();
            if(plano.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(plano);

        }
        
    }

}
