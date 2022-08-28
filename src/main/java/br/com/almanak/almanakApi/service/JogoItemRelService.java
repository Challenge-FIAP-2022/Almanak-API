package br.com.almanak.almanakApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.JogoItemRel;
import br.com.almanak.almanakApi.repository.JogoitemRelRepository;

@Service
public class JogoItemRelService {

    @Autowired
    JogoitemRelRepository repository;

    public Page<JogoItemRel> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<JogoItemRel> getById(Integer id){
        return repository.findById(id);
    }

    public void save(JogoItemRel contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }
    
}
