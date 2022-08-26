package br.com.almanak.almanakApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.almanak.almanakApi.model.Jogo;
import br.com.almanak.almanakApi.repository.JogoRepository;

public class JogoService {

    @Autowired
    JogoRepository repository;

    public Page<Jogo> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Jogo> getById(Integer id){
        return repository.findById(id);
    }
    public Optional<Jogo> fetchAvaliacoes(Integer id){
        return repository.fetchAvaliacoes(id);
    }

    public void save(Jogo contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }
    
}
