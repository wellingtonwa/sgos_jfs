/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author wellington
 */
@Embeddable
public class GrupoPermissaoTelaPK implements Serializable{
    
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Grupo grupo;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Tela tela;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Permissao permissao;

    public GrupoPermissaoTelaPK() {
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoPermissaoTelaPK other = (GrupoPermissaoTelaPK) obj;
        if (this.grupo != other.grupo && (this.grupo == null || !this.grupo.equals(other.grupo))) {
            return false;
        }
        if (this.tela != other.tela && (this.tela == null || !this.tela.equals(other.tela))) {
            return false;
        }
        if (this.permissao != other.permissao && (this.permissao == null || !this.permissao.equals(other.permissao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.grupo != null ? this.grupo.hashCode() : 0);
        hash = 11 * hash + (this.tela != null ? this.tela.hashCode() : 0);
        hash = 11 * hash + (this.permissao != null ? this.permissao.hashCode() : 0);
        return hash;
    }
    
}
