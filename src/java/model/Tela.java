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
public class Tela implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String descricao;
    
    @OneToMany(mappedBy="id.tela", cascade= CascadeType.ALL)
    private List<GrupoPermissaoTela> PermissoesTela;
    
    
    public Tela() {
    }

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

    public List<GrupoPermissaoTela> getPermissoesTela() {
        return PermissoesTela;
    }

    public void setPermissoesTela(List<GrupoPermissaoTela> PermissoesTela) {
        this.PermissoesTela = PermissoesTela;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tela other = (Tela) obj;
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
