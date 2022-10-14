package br.com.almanak.almanakApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value="select * from tb_usuario where ds_email = ?1 and ds_senha = ?2", nativeQuery = true) 
    Optional<Usuario> login(String email, String senha);

    @Query(value="select ds_email from tb_usuario where ds_email = ?1", nativeQuery = true) 
    Optional<Usuario> findByEmail(String email);

}
