package br.com.almanak.almanakApi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.PlanoUsuarioRel;
import br.com.almanak.almanakApi.repository.PlanoUsuarioRelRepository;

public class PlanoUsuarioRelService {

    @Autowired
    PlanoUsuarioRelRepository repository;

    public List<PlanoUsuarioRel> listAll(){
        return repository.findAll();
    }
    
    public Optional<PlanoUsuarioRel> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<List<PlanoUsuarioRel>> listByValid(EN_Booleano flag){
        return Optional.of(repository.listByValid(flag));
    }
    public void save(PlanoUsuarioRel planoUsuario){
        if(planoUsuario.getDtRegistro() == null)
            planoUsuario.setDtRegistro(LocalDateTime.now());
        repository.save(planoUsuario);
    }

    public void remove(Integer id){
        repository.deleteById(id);
    }
    
}
