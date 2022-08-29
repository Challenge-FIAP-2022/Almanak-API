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
    private Boolean maioridade;
    private PlanoDTO plano;
    // private List<JogoDTO> jogosRecomendados = new ArrayList<JogoDTO>();

    public UsuarioDTO convert(Usuario usuario){

        this.id = usuario.getId();
        usuario.setMaioridade();
        this.maioridade = usuario.isMaioridade();
        this.plano = new PlanoDTO().convert(usuario.getPlanoValido());

        return this;
        
    }

}
