package br.com.almanak.almanakApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.almanak.almanakApi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value="select * from tb_usuario where ds_email = ?1", nativeQuery = true) 
    List<Usuario> findByEmail(String email);

}
