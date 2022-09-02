package br.com.almanak.almanakApi.test;

import br.com.almanak.almanakApi.enumerator.EN_Tipo_Item;

public class Teste {

    public static void main(String[] args) {

        for(EN_Tipo_Item tp : EN_Tipo_Item.values()){
            
            System.out.println(tp.toString()+' '+tp.toString().equals("tabuleiro"));

        }



    }

}
