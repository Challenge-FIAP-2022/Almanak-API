package br.com.almanak.almanakApi.Interface;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.model.Usuario;
import br.com.almanak.almanakApi.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @JsonIgnore
    @Autowired
    UsuarioService uServiceDTO;

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

    public Usuario build(UsuarioDTO dto){
        Optional<Usuario> usuario = uServiceDTO.getById(id);

        if(usuario.isEmpty()){
            return usuario.get();
        }else{
            return null;
        }
            
    }
    
}
