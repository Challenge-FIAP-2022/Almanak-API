package br.com.almanak.almanakApi.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.almanak.almanakApi.model.Usuario;

public class Teste {

    public static void main(String[] args) {
        Usuario user = new Usuario("AlmanaK", "almanak", "almanak", LocalDate.now(), LocalDateTime.now());

        System.out.println(user);

    }

}
