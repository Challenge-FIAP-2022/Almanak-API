package br.com.almanak.almanakApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.Avaliacao;
import br.com.almanak.almanakApi.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository repository;

    public Optional<List<Avaliacao>> listByGame (Integer id){
        return repository.listByGame(id);
    }
    
}
