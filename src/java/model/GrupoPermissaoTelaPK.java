/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author wellington
 */
@Embeddable
public class GrupoPermissaoTelaPK implements Serializable{
    
    @ManyToOne(fetch= FetchType.LAZY)
    private Grupo grupo;
    @ManyToOne(fetch= FetchType.LAZY)
    private Tela tela;
    @ManyToOne(fetch= FetchType.LAZY)
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
    
}
