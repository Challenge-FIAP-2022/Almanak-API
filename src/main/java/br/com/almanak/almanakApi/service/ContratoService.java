package br.com.almanak.almanakApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Contrato;
import br.com.almanak.almanakApi.model.Plano;
import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.repository.ContratoRepository;

@Service
public class ContratoService {

    @Autowired
    ContratoRepository repository;

    @Autowired
    PlanoService planoService;

    public Page<Contrato> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Contrato> getById(Integer id){
        return repository.findById(id);
    }

    public void save(Contrato contrato){
        contrato.setDtRegistro();
        repository.save(contrato);
    }

    public void contracacao(Usuario usuario, Plano plano){
        
        Contrato contrato = new Contrato(usuario, plano, EN_Booleano.sim);
        contrato.setDtRegistro();
        repository.save(contrato);
    }

    public Optional<Usuario> contracacao(Usuario usuario, String nomePlano){
        
        Optional<Plano> plano = planoService.findByName(nomePlano);
        
        if(!plano.isEmpty()){

            Contrato contrato = new Contrato(usuario, null, EN_Booleano.sim);
            contrato.addPlano(plano.get());
            contrato.setDtRegistro();
            repository.save(contrato);
            return Optional.of(usuario);

        }else{
            return Optional.empty();
        }
        
    }
    
}
