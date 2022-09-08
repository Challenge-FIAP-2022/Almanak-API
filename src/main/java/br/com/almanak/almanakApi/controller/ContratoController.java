package br.com.almanak.almanakApi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.almanak.almanakApi.Interface.ContratoDTO;
import br.com.almanak.almanakApi.Interface.UsuarioDTO;
import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Contrato;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.ContratoService;
import br.com.almanak.almanakApi.service.PlanoService;
import br.com.almanak.almanakApi.service.UsuarioService;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @Autowired
    private PlanoService planoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<Contrato> index(Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContratoDTO> show(@PathVariable Integer id){
        Optional<Contrato> opt = service.getById(id);

        if(!opt.isEmpty()){
            Optional<ContratoDTO> dto = Optional.of(new ContratoDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("valido/{userID}")
    public ResponseEntity<ContratoDTO> findByUser(@PathVariable Integer id){
        Optional<Contrato> opt = service.findByUser(id);

        if(!opt.isEmpty()){
            Optional<ContratoDTO> dto = Optional.of(new ContratoDTO().convert(opt.get()));
            return ResponseEntity.of(dto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("contratar/{nomePlano}")
    public ResponseEntity<UsuarioDTO> contratacao(@RequestBody UsuarioDTO usuarioBody, @PathVariable String nomePlano){
        var optionalPlano = planoService.findByName(nomePlano);
        var optionalUsuario = usuarioService.getById(usuarioBody.getId());

        if(!optionalPlano.isEmpty() && !optionalUsuario.isEmpty()){
            Plano plano = optionalPlano.get();
            Usuario usuario = optionalUsuario.get();

            Contrato contrato = new Contrato(usuario, plano, EN_Booleano.sim);
            service.save(contrato);
            
            UsuarioDTO dto = new UsuarioDTO().convert(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(dto);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ContratoDTO> destroy(@PathVariable Integer id){
        var opt = service.remove(id);
        if(opt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            
            var contrato = opt.get();
            if(contrato.getId() == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

                Optional<ContratoDTO> dto = Optional.of(new ContratoDTO().convert(opt.get()));
                
            return ResponseEntity.of(dto);

        }
        
    }

}
