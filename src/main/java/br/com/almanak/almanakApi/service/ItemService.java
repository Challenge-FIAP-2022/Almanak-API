package br.com.almanak.almanakApi.service;

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

    public Optional<Item> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<Item> findByName(EN_Tipo_Item tipo, String nome){
        return repository.findByName(tipo, nome);
    }

    public void save(Item contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }
    
}
