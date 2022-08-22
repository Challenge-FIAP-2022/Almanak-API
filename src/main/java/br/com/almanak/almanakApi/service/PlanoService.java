package br.com.almanak.almanakApi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.repository.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    PlanoRepository repository;

    public List<Plano> listAll(){
        return repository.findAll();
    }

    public Optional<Plano> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<List<Plano>> listByValid(EN_Booleano flag){
        return Optional.of(repository.listByValid(flag));
    }

    public void save(Plano plano){
        if(plano.getDtRegistro() == null)
            plano.setDtRegistro(LocalDateTime.now());
        repository.save(plano);
    }

    public void remove(Integer id){
        repository.deleteById(id);
    }
    
}
