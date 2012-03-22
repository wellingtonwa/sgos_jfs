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
public class ComponenteOrdemServicoPK implements Serializable{

    
    @ManyToOne(fetch= FetchType.LAZY)
    private OrdemServico ordemServico;
    
    @ManyToOne(fetch= FetchType.LAZY)
    private Componente componente;

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public String toString() {
        return "ComponenteOrdemServicoPK{" + "ordemServico=" + ordemServico + ", componente=" + componente + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComponenteOrdemServicoPK other = (ComponenteOrdemServicoPK) obj;
        if (this.ordemServico != other.ordemServico && (this.ordemServico == null || !this.ordemServico.equals(other.ordemServico))) {
            return false;
        }
        if (this.componente != other.componente && (this.componente == null || !this.componente.equals(other.componente))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.ordemServico != null ? this.ordemServico.hashCode() : 0);
        hash = 37 * hash + (this.componente != null ? this.componente.hashCode() : 0);
        return hash;
    }
    
    
    
}
