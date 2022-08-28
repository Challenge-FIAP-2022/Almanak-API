package br.com.almanak.almanakApi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_atividade")
@SequenceGenerator(name="atividade", sequenceName="sq_atividade", allocationSize=1)
public class Atividade {

    @Id
    @Column(name="id_atividade")
    @GeneratedValue(generator="atividade", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nm_tabela_alterada")
    private String tabelaAlterada; 
    
    @Column(name="ds_atividade")
    private String desc;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_tipo_atividade")
    private TipoAtividade tipoAtividade;

    public void addTipoAtividade(TipoAtividade tipoAtividade){
        tipoAtividade.addToList(this);
    }

    public void addUsuario(Usuario usuario){
        usuario.addToList(this);
    }

    public Atividade(String tabelaAlterada, String desc) {
        this.tabelaAlterada = tabelaAlterada;
        this.desc = desc;
    }

    public void setDtRegistro() {
        if (this.dtRegistro == null)
            this.dtRegistro = LocalDateTime.now();
    }
    
}
