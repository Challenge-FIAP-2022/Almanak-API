package br.com.almanak.almanakApi.model;

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
@Table(name="tb_usuario_role")
@SequenceGenerator(name="usuariorole", sequenceName="sq_usuario_role", allocationSize=1)
public class UsuarioRoleRel {

    @Id
    @Column(name="id_usuario_role")
    @GeneratedValue(generator="usuariorole", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;

    public void addUsuario(Usuario usuario){
        usuario.addToList(this);
    }

    public void addRole(Role role){
        role.addToList(this);
    }

}
