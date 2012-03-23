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
@Table(name="ordem_servico_has_servico")
public class ServicoOrdemServico implements Serializable{
    
    @EmbeddedId
    private ServicoOrdemServicoPK id = new ServicoOrdemServicoPK();
    
    private Integer quantidade;

    public ServicoOrdemServico() {
    }

    public ServicoOrdemServicoPK getId() {
        return id;
    }

    public void setId(ServicoOrdemServicoPK id) {
        this.id = id;
    }
    
   
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServicoOrdemServico other = (ServicoOrdemServico) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}
