package br.com.almanak.almanakApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;
import br.com.almanak.almanakApi.model.Item;
import br.com.almanak.almanakApi.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;

    public Page<Item> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Item> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<Item> findByName(String nome){
        return repository.findByName(nome);
    }

    public Optional<List<Item>> listByType(String type){

        for(EN_Tipo_Item tp : EN_Tipo_Item.values()){

            if(tp.toString().equals(type)){
                System.out.println(tp.toString());
                return repository.listByType(tp);
            }
                
        }

        return Optional.empty();

    }

    public Optional<List<EN_Tipo_Item>> listDistinctTypes(){
        return repository.listDistinctTypes();
    }

    public void save(Item contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }
    
}
