package br.com.almanak.almanakApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.Categoria;
import br.com.almanak.almanakApi.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;
    
    public Page<Categoria> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public List<Categoria> listAll(){
        return repository.findAll();
    }

    public Optional<Categoria> getById(Integer id){
        return repository.findById(id);
    }

    public void save(Categoria contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }

    public Optional<Categoria> findByName (String name){
        return repository.findByName(name);
    }
}
