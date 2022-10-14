package br.com.almanak.almanakApi.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Decryption;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.UsuarioService;

public class Teste {
    @Autowired
    UsuarioService service;

    @Autowired
    Decryption decrypt;

    public static void main(String[] args) {
        for (Integer i = 1; i < 25; i++){
            
            Usuario usuario = UsuarioService.getById(i).get();

        }
    }
}
