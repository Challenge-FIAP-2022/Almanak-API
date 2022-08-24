package br.com.almanak.almanakApi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.AtividadeService;
import br.com.almanak.almanakApi.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AtividadeService atividadeService;
    
    @GetMapping
    public Page<Usuario> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("adj/{id}")
    public ResponseEntity<Usuario> showAdj(@PathVariable Integer id){
        return ResponseEntity.of(service.getByIdAdj(id));
    }

    @GetMapping("login")
    public ResponseEntity<Usuario> login(@RequestParam String email,@RequestParam String senha){
        var optional = service.login(email,senha);

        if(!optional.isEmpty()){
            Usuario usuario = optional.get();
            atividadeService.login(usuario);
            return ResponseEntity.of(Optional.of(usuario.ajustar()));
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("abrirApp/{id}")
    public ResponseEntity<Usuario> abrirApp(Integer id){
        var atividadeOpt = atividadeService.abrirApp(id);

        if(!atividadeOpt.isEmpty()){
            Usuario usuario = optional.get();
            atividadeService.abrirApp(usuario);
            return ResponseEntity.of(Optional.of(usuario.ajustar()));
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("cadastro")
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario){
        var optional = service.findByEmail(usuario.getEmail());

        if(optional.isEmpty()){

            service.save(usuario);
            atividadeService.login(usuario);
        
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usuario.ajustar());

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    
    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody @Valid Usuario newUsuario){
        var optionalEmail = service.findByEmail(newUsuario.getEmail());

        if(!optionalEmail.isEmpty()){
            var optional = service.getById(id);

            if(optional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            var usuario = optional.get();
            BeanUtils.copyProperties(newUsuario, usuario);
            usuario.setId(id);

            service.save(usuario);
            return ResponseEntity.ok(usuario.ajustar());

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Integer id){
        var optional = service.getById(id);

        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            
            // service.remove(id);
            // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
    
}
