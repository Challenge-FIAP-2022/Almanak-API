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
    private String desc;
    private String icone;
    private String imagem;

    public CategoriaDTO convert(Categoria categoria){
        
        this.id = categoria.getId();
        this.name = categoria.getName();
        this.desc = categoria.getDesc();
        this.icone = categoria.getIcone();
        this.imagem = categoria.getImagem();
        
        return this;
        
    }

    public CategoriaDTO(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public CategoriaDTO(String name, String desc, String icone, String imagem) {
        this.name = name;
        this.desc = desc;
        this.icone = icone;
        this.imagem = imagem;
    }
    
}
