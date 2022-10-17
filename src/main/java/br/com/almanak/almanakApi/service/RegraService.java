package br.com.almanak.almanakApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.Regra;
import br.com.almanak.almanakApi.repository.RegraRepository;

@Service
public class RegraService {

    @Autowired
    RegraRepository repository;

    public Page<Regra> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<List<Regra>> listResponse(Integer id, String jsonString){
        return repository.listResponse(id, jsonString);
    }

    public Optional<Regra> getById(Integer id){
        return repository.findById(id);
    }

    public void save(Regra contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }
    
}
