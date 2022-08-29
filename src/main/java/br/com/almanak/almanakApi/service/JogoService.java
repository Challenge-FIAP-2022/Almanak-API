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

    public Optional<Jogo> getById(Integer id){
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

/*  
    public Optional<List<Jogo>> listByCategoria(List<String> categorias){
        for(String s : categorias){
            s = s.toLowerCase();
        }
        return repository.listByCategoria(categorias);
    }

    public Optional<List<Jogo>> listByCategoria(String categoria){
        return repository.listByCategoria(categoria);
    }
 
 
    public Optional<List<JogoDTO>> findAllForUser(Usuario usuario, String nomePlano){

        usuario.setMaioridade();
        EN_Booleano maioridade = usuario.isMaioridade() ? EN_Booleano.sim : EN_Booleano.nao;
        EN_Booleano elite = (nomePlano.equals("Elite")) ? EN_Booleano.sim : EN_Booleano.nao;
        Optional<List<Jogo>> jogos =  repository.findAllForUser(maioridade, elite);

        return Optional.of(new JogoDTO().convertList(jogos.get()));

    }
*/
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
