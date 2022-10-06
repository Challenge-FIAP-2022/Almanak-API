package br.com.almanak.almanakApi.Interface;

import java.time.LocalDateTime;

import br.com.almanak.almanakApi.model.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {


    private Integer id;
    private UsuarioDTO usuarioDTO;
    private Double nota;
    private String desc;
    private LocalDateTime dtRegistro;

    public AvaliacaoDTO convert(Avaliacao avaliacao){
        
        this.id = avaliacao.getId();
        this.usuarioDTO =  new UsuarioDTO().convert(avaliacao.getUsuario());
        this.nota = avaliacao.getNota();
        this.desc = avaliacao.getDesc();
        this.dtRegistro = avaliacao.getDtRegistro();
        
        return this;
        
    }
    
}
