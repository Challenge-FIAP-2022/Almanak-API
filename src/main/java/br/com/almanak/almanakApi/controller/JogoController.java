package br.com.almanak.almanakApi.controller;

import java.util.ArrayList;
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
import br.com.almanak.almanakApi.utilities.Calculadora;


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
            Optional<Double> optScore = service.findScore(opt.get().getId());
            if(!optScore.isEmpty()){
                Double score = Calculadora.ajusteNota(optScore.get());
                dto.get().setScore(score);
            }
            
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("valido/{flag}")
    public ResponseEntity<List<JogoDTO>> listByValid(@PathVariable() EN_Booleano flag){
        Optional<List<Jogo>> opts = service.listByValid(flag);

        if(!opts.isEmpty()){
            List<Jogo> jogos = opts.get();
            List<JogoDTO> dtoList = new ArrayList<JogoDTO>();

            for(Jogo j : jogos){
                JogoDTO dto = new JogoDTO().convert(j);

                Optional<Double> optScore = service.findScore(j.getId());
                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    dto.setScore(score);
                }

                dto.setRegras(null);
                dto.setItens(null);

                dtoList.add(dto);

            }
    
            return ResponseEntity.ok().body(dtoList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
    }
        
    @GetMapping("nome/{nome}")
    public ResponseEntity<JogoDTO> findByName(@PathVariable String nome){
        Optional<Jogo> opt = service.findByName(nome);

        if(!opt.isEmpty()){
            
            Optional<JogoDTO> dto = Optional.of(new JogoDTO().convert(opt.get()));
            Optional<Double> optScore = service.findScore(opt.get().getId());
            if(!optScore.isEmpty()){
                Double score = Calculadora.ajusteNota(optScore.get());
                dto.get().setScore(score);
            }

            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
/*
    @GetMapping("categoria/{nomeCategoria}")
    public ResponseEntity<List<JogoDTO>> ffindByCategoria(@PathVariable String categoria){
        Optional<List<Jogo>> opt = service.listByCategoria(categoria);
        
        if(!opt.isEmpty()){
            List<Jogo> optList = opt.get();
            List<JogoDTO> jogosDTO = new ArrayList<JogoDTO>();

            for(Jogo j : optList){
                JogoDTO jogoDTO = new JogoDTO().convert(j);
                Optional<Double> optScore = service.findScore(j.getId());

                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    jogoDTO.setScore(score);
                }

                jogoDTO.setRegras(null);
                jogoDTO.setCategorias(null);
                jogoDTO.setItens(null);

                jogosDTO.add(jogoDTO);

            }

            return ResponseEntity.ok(jogosDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    @GetMapping("categoria")
    public ResponseEntity<List<JogoDTO>> findByListCategoria(@RequestBody List<String> categorias){
        Optional<List<Jogo>> opt = service.listByCategoria(categorias);
        
        if(!opt.isEmpty()){
            List<Jogo> optList = opt.get();
            List<JogoDTO> jogosDTO = new ArrayList<JogoDTO>();


            for(Jogo j : optList){
                JogoDTO jogoDTO = new JogoDTO().convert(j);
                Optional<Double> optScore = service.findScore(j.getId());

                if(!optScore.isEmpty()){
                    Double score = Calculadora.ajusteNota(optScore.get());
                    jogoDTO.setScore(score);
                }

                jogoDTO.setRegras(null);
                jogoDTO.setCategorias(null);
                
                jogoDTO.setItens(null);

                jogosDTO.add(jogoDTO);

            }

            return ResponseEntity.ok(jogosDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

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

    @DeleteMapping("{id}")
    public ResponseEntity<Jogo> destroy(@PathVariable Integer id){
        var jogoResponse = service.remove(id);
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
