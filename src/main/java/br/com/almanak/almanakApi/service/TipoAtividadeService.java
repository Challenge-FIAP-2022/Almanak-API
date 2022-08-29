package br.com.almanak.almanakApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.TipoAtividade;
import br.com.almanak.almanakApi.repository.TipoAtividadeRepository;

@Service
public class TipoAtividadeService {

    @Autowired
    TipoAtividadeRepository repository;

    public Page<TipoAtividade> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<TipoAtividade> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<TipoAtividade> findByName(String nome){
        return repository.findByName(nome);
    }

    public Optional<TipoAtividade> login(){
        return repository.login();
    }

    
    public Optional<TipoAtividade> abrirApp(){
        return repository.abrirApp();
    }

    public Optional<TipoAtividade> cadastrarCartao(){
        return repository.cadastrarCartao();
    }

    public void save(TipoAtividade tipoAtividade){
        tipoAtividade.setDtRegistro();
        repository.save(tipoAtividade);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
