package br.com.almanak.almanakApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.Interface.AvaliacaoDTO;
import br.com.almanak.almanakApi.model.Avaliacao;
import br.com.almanak.almanakApi.service.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {
    
    @Autowired
    private AvaliacaoService service;

    @GetMapping("jogo/{id}")
    public ResponseEntity<List<AvaliacaoDTO>> findById(@PathVariable Integer id){

        Optional<List<Avaliacao>> optList = service.listByGame(id);
        List<AvaliacaoDTO> dtoList = new ArrayList<AvaliacaoDTO>();

        if(!optList.isEmpty() && optList.get().size() > 0){

            for(Avaliacao a : optList.get()){

                AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO().convert(a);
                dtoList.add(avaliacaoDTO);

            }

            return ResponseEntity.ok().body(dtoList);

        }
            

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
