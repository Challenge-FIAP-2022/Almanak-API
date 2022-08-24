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

    @Autowired
    UsuarioService usuarioService;

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

    public Optional<Usuario> contracacao(Usuario usuario, String nomePlano){
        
        Optional<Plano> planoOpt = planoService.findByName(nomePlano);
        Optional<Usuario> usuarioOpt = usuarioService.getById(usuario.getId());
        
        if(!planoOpt.isEmpty() && usuarioOpt.isEmpty()){

            usuario = usuarioOpt.get();
            Plano plano = planoOpt.get();
            Contrato contrato = new Contrato(null, null, EN_Booleano.sim);
            contrato.addDependencies(usuario, plano);
            contrato.setDtRegistro();
            repository.save(contrato);
            return Optional.of(usuario);

        }else{
            return Optional.empty();
        }
        
    }

    public Optional<Contrato> remove(Integer id){

        Optional<Contrato> optional = repository.findById(id);

        if(!optional.isEmpty()){

            var contrato = optional.get();

            if(contrato.getDtEncerramento() == null){

                Usuario usuario = contrato.getUsuario();
                Plano plano = planoService.findByName("Gamer").get(); 
                Contrato contratoNew = new Contrato(null, null, EN_Booleano.sim);
                contratoNew.addDependencies(usuario, plano);
                contratoNew.setDtRegistro();
                repository.save(contratoNew);

                return Optional.of(contratoNew);

            }else{

                return Optional.of(new Contrato());
            }

        }else{

            return Optional.empty();
        }
            
    }
    
}
