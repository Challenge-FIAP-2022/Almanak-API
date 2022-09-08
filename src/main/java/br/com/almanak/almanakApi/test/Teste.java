package br.com.almanak.almanakApi.test;

import java.util.ArrayList;
import java.util.List;

import br.com.almanak.almanakApi.Interface.FilterDTO;
import br.com.almanak.almanakApi.enumerator.EN_Booleano;

public class Teste {

    public static void main(String[] args) {

        FilterDTO filter = new FilterDTO();

        filter.setMaioridade(EN_Booleano.sim);

        List<String> categorias = new ArrayList<>();
        categorias.add("categoria1");
        categorias.add("categoria2");
        categorias.add("categoria3");


        List<String> itens = new ArrayList<>();
        itens.add("item1");

        filter.setCategorias(categorias);
        filter.setItens(itens);

        System.out.println(filter.buildSQLFilter());

    }

}
