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

import br.com.almanak.almanakApi.Interface.CategoriaDTO;
import br.com.almanak.almanakApi.model.Categoria;
import br.com.almanak.almanakApi.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;

    @GetMapping
    public Page<Categoria> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("adj")
    public ResponseEntity<List<CategoriaDTO>> show(){

        List<Categoria> categorias = service.listAll();
        List<CategoriaDTO> dtoList = new ArrayList<CategoriaDTO>();

        for(Categoria c : categorias){
            dtoList.add(new CategoriaDTO().convert(c));
        }

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id){

        Optional<Categoria> opt = service.findById(id);

        if(!opt.isEmpty()){
            Optional<CategoriaDTO> dto = Optional.of(new CategoriaDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<CategoriaDTO> findByName(@PathVariable String nome){

        Optional<Categoria> opt = service.findByName(nome);

        if(!opt.isEmpty()){
            Optional<CategoriaDTO> dto = Optional.of(new CategoriaDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody @Valid CategoriaDTO categoriaDto){

        Optional<Categoria> opt = service.findByName(categoriaDto.getName());

        if(opt.isEmpty()){

            Categoria categoria  = new Categoria(categoriaDto.getName(), categoriaDto.getIcone(), categoriaDto.getImagem(), categoriaDto.getDesc());
            service.save(categoria);
            CategoriaDTO dto = new CategoriaDTO().convert(categoria);
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(dto);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping()
    public ResponseEntity<CategoriaDTO> update(@RequestBody @Valid Categoria newCategoria){
        Optional<Categoria> opt = service.findById(newCategoria.getId());

        if(opt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Categoria categoria = opt.get();
        BeanUtils.copyProperties(newCategoria, categoria);

        service.save(categoria);
        Optional<CategoriaDTO> dto = Optional.of(new CategoriaDTO().convert(categoria));

        return ResponseEntity.of(dto);
        
    }

}
