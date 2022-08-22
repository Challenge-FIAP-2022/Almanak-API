package br.com.almanak.almanakApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.almanak.almanakApi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
