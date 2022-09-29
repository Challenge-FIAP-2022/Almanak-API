package br.com.almanak.almanakApi.Interface;

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
    
}
