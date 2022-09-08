package br.com.almanak.almanakApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Jogo;
import br.com.almanak.almanakApi.repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    JogoRepository repository;

    public Page<Jogo> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Jogo> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<Jogo> findByName(String nome){
        return repository.findByName(nome);
    }

    public Optional<List<Jogo>> listByValid(EN_Booleano flag){
        return repository.listByValid(flag);
    }

    public Optional<Double> findScore(String name){
        return repository.findScore(name);
    }

    public Optional<Double> findScore(Integer id){
        return repository.findScore(id);
    }

    public Optional<List<Jogo>> listByCategoria(String categoria){
        return repository.listByCategoria(categoria);
    }

    public Optional<List<Jogo>> listByListCategoria(List<String> categorias){

        for(String s : categorias){
            s = s.toLowerCase();
        }

        return repository.listByListCategoria(categorias);
    }

    public Optional<List<Jogo>> listRecommended(Integer id){
        return repository.listRecommended(id);
    }

    // public Optional<List<Jogo>> listByListFilters(FilterDTO filtroDTO){

    //     String filter = filtroDTO.buildSQLFilter();

    //     return repository.listByListFilters(filter);
    // }

    public void save(Jogo jogo){
        jogo.setDtRegistro();
        repository.save(jogo);
    }
    
    public Optional<Jogo> remove(Integer id){

        Optional<Jogo> optional = repository.findById(id);

        if(!optional.isEmpty()){

            var jogo = optional.get();

            if(jogo.getDtEncerramento() == null){

                jogo.setDtEncerramento();
                repository.save(jogo);
                return Optional.of(jogo);

            }else{

                return Optional.of(new Jogo());
            }

        }else{

            return Optional.empty();
        }
            
    }

    public Optional<Jogo> remove(String nome){

        Optional<Jogo> optional = repository.findByName(nome);

        if(!optional.isEmpty()){

            var jogo = optional.get();

            if(jogo.getDtEncerramento() == null){

                jogo.setDtEncerramento();
                repository.save(jogo);
                return Optional.of(jogo);

            }else{

                return Optional.of(new Jogo());
            }

        }else{

            return Optional.empty();
        }
            
    }

}
