package br.com.almanak.almanakApi.test;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.UsuarioService;

public class Teste {

    public static void main(String[] args) {

        UsuarioService service = new UsuarioService();

        for (Integer i = 1; i < 25; i++){

            System.out.println(i);
            
            Usuario usuario = service.getById(1).get();

            System.out.println(usuario.getName());

            // usuario.setSenha(usuario.getSenha());

            // service.save(usuario);

        }
    }
}
