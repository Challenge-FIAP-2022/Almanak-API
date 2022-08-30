package br.com.almanak.almanakApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Item;
import br.com.almanak.almanakApi.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public Page<Item> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> show(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("tipoitem")
    public ResponseEntity<List<String>> listTipoItem(){
        return ResponseEntity.of(null);
    }
    
}
