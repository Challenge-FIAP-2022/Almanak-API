package br.com.almanak.almanakApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_role")
@SequenceGenerator(name="role", sequenceName="sq_role", allocationSize=1)
public class Role implements GrantedAuthority {

    @Id
    @Column(name="id_role")
    @GeneratedValue(generator="role", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nm_role")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="role", cascade = CascadeType.ALL)
    private List<UsuarioRoleRel> roles = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public void addToList(UsuarioRoleRel rel){
        rel.setRole(this);
        this.roles.add(rel);
    }

}
