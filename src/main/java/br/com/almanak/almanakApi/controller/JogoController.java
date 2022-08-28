package br.com.almanak.almanakApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.almanak.almanakApi.Interface.JogoDTO;
import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Jogo;
import br.com.almanak.almanakApi.service.JogoService;


@Controller
@RequestMapping("/api/jogo")
public class JogoController {

    @Autowired
    private JogoService service;
 
    @GetMapping
    public Page<Jogo> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<JogoDTO> show(@PathVariable Integer id){
        Optional<Jogo> opt = service.getById(id);

        if(!opt.isEmpty()){
            Optional<JogoDTO> dto = Optional.of(new JogoDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("valido/{flag}")
    public ResponseEntity<List<JogoDTO>> listByValid(@PathVariable() EN_Booleano flag){
        Optional<List<Jogo>> opts = service.listByValid(flag);

        if(!opts.isEmpty()){
            List<Jogo> jogos = opts.get();
            List<JogoDTO> dtos = new JogoDTO().convertList(jogos);
            return ResponseEntity.ok(dtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        
    @GetMapping("nome/{nome}")
    public ResponseEntity<JogoDTO> findByName(@PathVariable String nome){
        Optional<Jogo> jogo = service.findByName(nome);
        
        if(!jogo.isEmpty())
            return ResponseEntity.ok(new JogoDTO().convert(jogo.get()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /*
    @PostMapping
    public ResponseEntity<JogoDTO> create(@RequestBody @Valid Jogo jogo){
        var optionalName = service.findByName(jogo.getName());
        if(optionalName.isEmpty()){
            service.save(jogo);
            JogoDTO dto = new JogoDTO().convert(jogo);
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(dto);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<JogoDTO> update(@PathVariable Integer id, @RequestBody @Valid Jogo newJogo){
        var optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var jogo = optional.get();
        BeanUtils.copyProperties(newJogo, jogo);
        jogo.setId(id);

        service.save(jogo);
        Optional<JogoDTO> dto = Optional.of(new JogoDTO().convert(jogo));

        return ResponseEntity.of(dto);
        
    }

    */

    @DeleteMapping("byname/{nome}")
    public ResponseEntity<Jogo> destroyByName(@PathVariable String nome){
        var jogoResponse = service.remove(nome);
        if(jogoResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var jogo = jogoResponse.get();
            if(jogo.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(jogo);

        }
        
    }

    @DeleteMapping("{nome}")
    public ResponseEntity<Jogo> destroy(@PathVariable String nome){
        var jogoResponse = service.remove(nome);
        if(jogoResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var jogo = jogoResponse.get();
            if(jogo.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                
            return ResponseEntity.ok(jogo);

        }
        
    }
    
}
