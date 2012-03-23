/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author wellington
 */
@Entity
@Table(name="grupo_has_permissao_on_tela")
public class GrupoPermissaoTela implements Serializable{
    
    @EmbeddedId
    private GrupoPermissaoTelaPK id = new GrupoPermissaoTelaPK();

    public GrupoPermissaoTela() {
    }

    public GrupoPermissaoTelaPK getId() {
        return id;
    }

    public void setId(GrupoPermissaoTelaPK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoPermissaoTela other = (GrupoPermissaoTela) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
}
