package br.com.almanak.almanakApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.Atividade;
import br.com.almanak.almanakApi.model.TipoAtividade;
import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.repository.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    AtividadeRepository repository;

    @Autowired
    TipoAtividadeService tipoAtividadeService;

    public Page<Atividade> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Atividade> getById(Integer id){
        return repository.findById(id);
    }

    public void save(Atividade tipoAtividade){
        tipoAtividade.setDtRegistro();
        repository.save(tipoAtividade);
    }

    public void login(Usuario usuario){
        
        Optional<TipoAtividade> tipoAtividade = tipoAtividadeService.login();
        Atividade atividade = new Atividade(null,null);
        atividade.addUsuario(usuario);
        
        if(!tipoAtividade.isEmpty())
            atividade.addTipoAtividade(tipoAtividade.get());

        atividade.setDtRegistro();
        repository.save(atividade);
    }
    
}
