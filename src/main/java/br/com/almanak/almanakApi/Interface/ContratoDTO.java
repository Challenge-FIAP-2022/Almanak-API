package br.com.almanak.almanakApi.Interface;

import java.time.LocalDateTime;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Contrato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {
    
    private Integer id;
    private EN_Booleano valido;
    private LocalDateTime dtEncerramento;
    private LocalDateTime dtRegistro;
    private UsuarioDTO usuario;

    public ContratoDTO convert(Contrato contrato){
        
        this.id = contrato.getId();
        this.valido = contrato.getValido();
        this.dtEncerramento = contrato.getDtEncerramento();
        this.dtRegistro = contrato.getDtRegistro();
        UsuarioDTO dto = new UsuarioDTO().convert(contrato.getUsuario());
        dto.setPlano(new PlanoDTO().convert(contrato.getPlano()));
        this.usuario  = dto;
        

        return this;
        
    }

}
