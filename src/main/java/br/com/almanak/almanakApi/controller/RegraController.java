package br.com.almanak.almanakApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.Interface.RegraDTO;
import br.com.almanak.almanakApi.model.Regra;
import br.com.almanak.almanakApi.service.RegraService;

@RestController
@RequestMapping("/api/regra")
public class RegraController {

    @Autowired
    private RegraService service;

    @PostMapping("resposta/{id}")
    public ResponseEntity<List<RegraDTO>> listResponse(@PathVariable Integer id, @RequestBody @Valid String jsonString){
        Optional<List<Regra>> opt = service.listResponse(id, jsonString);
        
        if(!opt.isEmpty()){
            List<Regra> regras = opt.get();
            List<RegraDTO> dtoList = new ArrayList<RegraDTO>();

            for(Regra r : regras){
                RegraDTO dto = new RegraDTO().convert(r);
                dtoList.add(dto);
            }

            return ResponseEntity.ok().body(dtoList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
