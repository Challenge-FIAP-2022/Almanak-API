package br.com.almanak.almanakApi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;

    public List<Usuario> listAll(){
        return repository.findAll();
    }
    
    public Optional<Usuario> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<Usuario> getByIdAdj(Integer id){
        return Optional.of(repository.findById(id).get().ajustar());
    }

    public Optional<Usuario> getByEmailAdj(String email){
        return Optional.of(repository.findByEmail(email).get(0).ajustar());
    }

    public void save(Usuario usuario){
        if(usuario.getDtRegistro() == null)
            usuario.setDtRegistro(LocalDateTime.now());
        repository.save(usuario);
    }

    public void remove(Integer id){
        repository.deleteById(id);
    }

}
