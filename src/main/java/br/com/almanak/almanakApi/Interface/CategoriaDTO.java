package br.com.almanak.almanakApi.Interface;

import br.com.almanak.almanakApi.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Integer id;
    private String name;
    private String icone;
    private String imagem;
    private String desc;

    public CategoriaDTO convert(Categoria categoria){
        
        this.id = categoria.getId();
        this.name = categoria.getName();
        this.icone = categoria.getIcone();
        this.imagem = categoria.getImagem();
        this.desc = categoria.getDesc();
        
        return this;
        
    }
    
}
