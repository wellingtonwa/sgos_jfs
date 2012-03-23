/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author wellington
 */
@Entity
public class Permissao implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable=false, length=20, unique=true)
    private String permissao;
    
    @ManyToMany(mappedBy = "permissoes")
    private List<Grupo> grupos;
    
    @OneToMany(mappedBy="id.permissao", cascade= CascadeType.ALL)
    private List<GrupoPermissaoTela> PermissoesPermissao;
    
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public List<GrupoPermissaoTela> getPermissoesPermissao() {
        return PermissoesPermissao;
    }

    public void setPermissoesPermissao(List<GrupoPermissaoTela> PermissoesPermissao) {
        this.PermissoesPermissao = PermissoesPermissao;
    }
            
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permissao other = (Permissao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
 }
