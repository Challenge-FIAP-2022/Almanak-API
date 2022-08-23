package br.com.almanak.almanakApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Plano>> listByValid(@PathVariable() EN_Booleano flag){
            return ResponseEntity.of(service.listByValid(flag));
    }
        

    @GetMapping("nome/{nome}")
    public ResponseEntity<Plano> findByName(@PathVariable String nome){
        return ResponseEntity.of(service.findByName(nome));
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
        var optionalName = service.findByName(newPlano.getName());

        if(optionalName.isEmpty()){
            var optional = service.getById(id);

            if(optional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            var plano = optional.get();
            BeanUtils.copyProperties(newPlano, plano);
            plano.setId(id);

            service.save(plano);
            return ResponseEntity.ok(plano);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Plano> destroy(@PathVariable Integer id){
        var planoResponse = service.remove(id);
        if(planoResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var plano = planoResponse.get();
            if(plano.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(plano);

        }
        
    }

    @GetMapping("teste/{flag}")
    public EN_Booleano teste(@PathVariable() EN_Booleano flag){
        return flag;
    }

}
