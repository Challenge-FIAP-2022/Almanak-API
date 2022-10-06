package br.com.almanak.almanakApi.Interface;

import br.com.almanak.almanakApi.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private String nome;
    private Boolean maioridade;
    private Integer grupo;
    private PlanoDTO plano;

    public UsuarioDTO convert(Usuario usuario){

        this.id = usuario.getId();
        this.nome = usuario.getName();
        usuario.setMaioridade();
        this.maioridade = usuario.isMaioridade();
        this.grupo = usuario.getGrupoValido().getId();
        this.plano = new PlanoDTO().convert(usuario.getPlanoValido());

        return this;
        
    }

}
