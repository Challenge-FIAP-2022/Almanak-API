package br.com.almanak.almanakApi.controller;

import java.util.List;

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

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> index(){
        return service.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("adj/{id}")
    public ResponseEntity<Usuario> showAdj(@PathVariable Integer id){
        return ResponseEntity.of(service.getByIdAdj(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario){
        service.save(usuario);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody @Valid Usuario newUsuario){
        var optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var usuario = optional.get();
        BeanUtils.copyProperties(newUsuario, usuario);
        usuario.setId(id);

        service.save(usuario);
        return ResponseEntity.ok(usuario);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Integer id){
        var optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
