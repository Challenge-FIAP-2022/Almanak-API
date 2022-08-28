package br.com.almanak.almanakApi.Interface;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.almanak.almanakApi.enumerator.EN_Booleano;
import br.com.almanak.almanakApi.model.Contrato;
import br.com.almanak.almanakApi.service.ContratoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {
    
    @JsonIgnore
    @Autowired
    ContratoService cServiceDTO;

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
        this.usuario  = new UsuarioDTO().convert(contrato.getUsuario());

        return this;
        
    }

    public Contrato build(ContratoDTO dto){
        Optional<Contrato> contrato = cServiceDTO.getById(id);

        if(contrato.isEmpty()){
            return contrato.get();
        }else{
            return null;
        }
            
    }

}
