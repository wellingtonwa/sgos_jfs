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
    
}
