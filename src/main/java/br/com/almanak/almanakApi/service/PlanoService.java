package br.com.almanak.almanakApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.repository.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    PlanoRepository repository;

    public Page<Plano> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Plano> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<List<Plano>> listByValid(EN_Booleano flag){
        return repository.listByValid(flag);
    }

    public Optional<Plano> findByName(String nome){
        return repository.findByName(nome);
    }

    public void save(Plano plano){
        plano.setDtRegistro();
        repository.save(plano);
    }

    public Optional<Plano> remove(String nome){

        Optional<Plano> planoOptional = repository.findByName(nome);

        if(!planoOptional.isEmpty()){

            var plano = planoOptional.get();

            if(plano.getDtEncerramento() == null){

                plano.setDtEncerramento();
                repository.save(plano);

                return Optional.of(plano);

            }else{

                return Optional.of(new Plano());
            }

        }else{

            return Optional.empty();
        }
            
    }

    public Optional<Plano> remove(Integer id){

        Optional<Plano> planoOptional = repository.findById(id);

        if(!planoOptional.isEmpty()){

            var plano = planoOptional.get();

            if(plano.getValido() == EN_Booleano.sim){

                plano.setDtEncerramento();
                repository.save(plano);

                return Optional.of(plano);

            }else{

                return Optional.of(new Plano());
            }

        }else{

            return Optional.empty();
        }
            
    }
    
}
