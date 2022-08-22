package br.com.almanak.almanakApi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_usuario")
@SequenceGenerator(name="usuario", sequenceName="sq_usuario", allocationSize=1)
public class Usuario {
   
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(generator="usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nm_usuario")
    private String name;

    @Column(name="ds_email")
    private String email;

    @Column(name="ds_senha")
    private String senha;

    @Column(name="dt_nascimento")
    private LocalDate dtNascimento;

    @Column(name="dt_registro")
    private LocalDateTime dtRegistro;

    @Transient
    private boolean fl_idade;

    public Usuario ajustar(){
        Usuario retorno = this;
        retorno.setFl_idade();
        retorno.setName(null);
        retorno.setEmail(null);
        retorno.setSenha(null);
        retorno.setDtNascimento(null);
        retorno.setDtRegistro(null);
        return retorno;
    }

    public Usuario(Integer id, String name, String email, String senha, LocalDate dtNascimento,
            LocalDateTime dtRegistro, boolean fl_idade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
        this.fl_idade = fl_idade;
    }

    public Usuario(Integer id, String name, String email, String senha, LocalDate dtNascimento) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
    }

    public Usuario(String name, String email, String senha, LocalDate dtNascimento, LocalDateTime dtRegistro) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.dtRegistro = dtRegistro;
    }

    public Usuario(String name, String email, String senha, LocalDate dtNascimento) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
    }
    
    public Usuario(Integer id, boolean fl_idade) {
        this.id = id;
        this.fl_idade = fl_idade;
    }

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public LocalDateTime getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(LocalDateTime dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public boolean isFl_idade() {
        return fl_idade;
    }

    public void setFl_idade() {
        this.fl_idade = ChronoUnit.YEARS.between(dtNascimento, LocalDate.now()) > 18;
    }

    @Override
    public String toString() {
        return "Usuario [dtNascimento=" + dtNascimento + ", dtRegistro=" + dtRegistro + ", email=" + email
                + ", fl_idade=" + fl_idade + ", id=" + id + ", name=" + name + ", senha=" + senha + "]";
    }

}
