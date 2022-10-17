package br.com.almanak.almanakApi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.Interface.PlanoDTO;
import br.com.almanak.almanakApi.Interface.UsuarioDTO;
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

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping
    public Page<Usuario> index(Pageable pageable){
        return service.listAll(pageable);
    }
            
    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> show(@PathVariable Integer id){
        Optional<Usuario> opt = service.getById(id);

        if(!opt.isEmpty()){
            Optional<UsuarioDTO> dto = Optional.of(new UsuarioDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("adj/{id}")
    public ResponseEntity<Usuario> showAdj(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("login")
    public ResponseEntity<UsuarioDTO> login(@RequestParam String email,@RequestParam String senha){
        senha = passwordEncoder.encode(senha);
        var optional = service.login(email,senha);

        if(!optional.isEmpty()){
            Usuario usuario = optional.get();
            atividadeService.login(usuario);
            Optional<UsuarioDTO> dto = Optional.of(new UsuarioDTO().convert(usuario));
            return ResponseEntity.of(dto);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("enterapp/{id}")
    public ResponseEntity<UsuarioDTO> abrirApp(@PathVariable Integer id){
        var optional = service.getById(id);

        if(!optional.isEmpty()){
            Usuario usuario = optional.get();
            atividadeService.abrirApp(usuario);
            Optional<UsuarioDTO> dto = Optional.of(new UsuarioDTO().convert(usuario));
            return ResponseEntity.of(dto);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("cadastrocartao/{id}")
    public ResponseEntity<UsuarioDTO> cadastrarCartao(@PathVariable Integer id){
        var optional = service.getById(id);

        if(!optional.isEmpty()){
            Usuario usuario = optional.get();
            atividadeService.cadastrarCartao(usuario);
            Optional<UsuarioDTO> dto = Optional.of(new UsuarioDTO().convert(usuario));
            return ResponseEntity.of(dto);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("signin")
    public ResponseEntity<UsuarioDTO> create(@RequestBody @Valid Usuario usuario){
        var optional = service.findByEmail(usuario.getEmail());

        if(optional.isEmpty()){
            
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            service.save(usuario);
            atividadeService.login(usuario);
            
            UsuarioDTO dto = new UsuarioDTO().convert(usuario);
            PlanoDTO plano = new PlanoDTO();
            plano.setId(1);
            plano.setName("Gamer");
            plano.setValor(0d);
            dto.setPlano(plano);
        
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(dto);

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    
    @PutMapping()
    public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid Usuario newUsuario){
        
        var opt = service.getById(newUsuario.getId());

        if(!opt.isEmpty()){
            Usuario usuario = opt.get();

            BeanUtils.copyProperties(newUsuario, usuario, new String [] {"id", "senha"});

            service.save(usuario);

            Optional<UsuarioDTO> dto = Optional.of(new UsuarioDTO().convert(usuario));
            
            return ResponseEntity.of(dto);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
