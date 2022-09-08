package br.com.almanak.almanakApi.Interface;

import java.util.ArrayList;
import java.util.List;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    
    private EN_Booleano maioridade;
    private Integer jogadores;
    private List<String> categorias;
    private List<String> itens;

    public String buildSQLFilter(){

        List<String> sqlFilters = new ArrayList<>();
        String s = new String();


        if(this.maioridade == null){
            s = "";
        }else{
            s = "j.maioridade = " + this.maioridade.toString();
        }
        sqlFilters.add(s);

        if(this.jogadores == null){
            s = "";
        }else{
            s = "j.minJogadores <= " + this.jogadores;
        }
        sqlFilters.add(s);

        if(this.categorias == null){
            s = "";
        }else{
            s = "c.name in (";
            for(String string : this.categorias){
                s = s + string + ", ";

            }
            s = s + " final)";
        }
        sqlFilters.add(s);

        if(this.categorias == null){
            s = "";
        }else{
            s = "i.name in (";
            for(String string : this.itens){
                s = s + string + ", ";

            }
            s = s + " final)";
        }
        sqlFilters.add(s);

        s = " 'n' ";

        for(Integer i = 0; i < sqlFilters.size(); i++){
            if(!sqlFilters.get(i).equals("")){
                sqlFilters.set(i, " and " + sqlFilters.get(i) + " ");
                s = s + sqlFilters.get(i);
            }            
        }

        return s;

    }
    
}
