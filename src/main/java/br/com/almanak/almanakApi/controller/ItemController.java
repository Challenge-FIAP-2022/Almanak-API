package br.com.almanak.almanakApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.Interface.ItemDTO;
import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import br.com.almanak.almanakApi.model.Item;
import br.com.almanak.almanakApi.model.Plano;
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
    public ResponseEntity<ItemDTO> show(@PathVariable Integer id){
        Optional<Item> opt = service.findById(id);

        if(!opt.isEmpty()){
            Optional<ItemDTO> dto = Optional.of(new ItemDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<ItemDTO> findByName(@PathVariable String nome){
        Optional<Item> opt = service.findByName(nome);

        if(!opt.isEmpty()){
            Optional<ItemDTO> dto = Optional.of(new ItemDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("tipo")
    public ResponseEntity<List<EN_Tipo_Item>> listDistinctTypes(){
        return ResponseEntity.of(service.listDistinctTypes());
    }

    @GetMapping("tipo/{tipo}")
    public ResponseEntity<List<ItemDTO>> listByType(@PathVariable String tipo){
        Optional<List<Item>> opts = service.listByType(tipo);

        if(!opts.isEmpty()){
            List<Item> itens = opts.get();
            List<ItemDTO> dtoList = new ArrayList<ItemDTO>();

            for(Item i : itens){
                ItemDTO dto = new ItemDTO().convert(i);
                dtoList.add(dto);
            }

            return ResponseEntity.ok().body(dtoList);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<ItemDTO> create(@RequestBody @Valid ItemDTO itemDTO){

        Optional<Item> opt = service.findByName(itemDTO.getName());

        if(opt.isEmpty()){

            Item Item  = new Item(itemDTO.getTipo(), itemDTO.getName());
            service.save(Item);
            ItemDTO dto = new ItemDTO().convert(Item);
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(dto);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping()
    public ResponseEntity<ItemDTO> update(@RequestBody @Valid Plano newItem){
        Optional<Item> opt = service.findById(newItem.getId());

        if(opt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Item Item = opt.get();
        BeanUtils.copyProperties(newItem, Item);

        service.save(Item);
        Optional<ItemDTO> dto = Optional.of(new ItemDTO().convert(Item));

        return ResponseEntity.of(dto);
        
    }
    
}
