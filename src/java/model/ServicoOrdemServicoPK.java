/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author wellington
 */
@Embeddable
public class ServicoOrdemServicoPK implements Serializable{
    
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private OrdemServico ordemServico;
    
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Servico servico;

    public ServicoOrdemServicoPK() {
    }

    public ServicoOrdemServicoPK(OrdemServico ordemServico, Servico servico) {
        this.ordemServico = ordemServico;
        this.servico = servico;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServicoOrdemServicoPK other = (ServicoOrdemServicoPK) obj;
        if (this.ordemServico != other.ordemServico && (this.ordemServico == null || !this.ordemServico.equals(other.ordemServico))) {
            return false;
        }
        if (this.servico != other.servico && (this.servico == null || !this.servico.equals(other.servico))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.ordemServico != null ? this.ordemServico.hashCode() : 0);
        hash = 79 * hash + (this.servico != null ? this.servico.hashCode() : 0);
        return hash;
    }

    
    
}
