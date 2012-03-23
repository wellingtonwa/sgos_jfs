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
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    @Column(unique=true, length=60, nullable=false)
    private String nome;
    @Column(length=500)
    private String descricao;
    
    
    @ManyToMany(mappedBy = "grupos")
    private List<Usuario> usuarios;
    
    @ManyToMany(cascade= CascadeType.ALL)
    private List<Permissao> permissoes;
    
    @OneToMany(mappedBy="id.grupo", cascade= CascadeType.ALL)
    private List<GrupoPermissaoTela> PermissoesGrupo;
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<GrupoPermissaoTela> getPermissoesGrupo() {
        return PermissoesGrupo;
    }

    public void setPermissoesGrupo(List<GrupoPermissaoTela> PermissoesGrupo) {
        this.PermissoesGrupo = PermissoesGrupo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
        
}
