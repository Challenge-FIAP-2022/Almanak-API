package br.com.almanak.almanakApi.service;

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

    public void save(Usuario usuario){
        repository.save(usuario);
    }

    public void remove(Integer id){
        repository.deleteById(id);
    }

    public Optional<Usuario> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<Usuario> getByIdAdj(Integer id){
        Optional<Usuario> usuario = repository.findById(id);
        usuario = Optional.of(usuario.get().ajustar());
        return usuario;
    }

}
