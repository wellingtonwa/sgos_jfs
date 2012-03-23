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
@Table(name="ordem_servico_has_componente")
public class ComponenteOrdemServico implements Serializable{
    
    @EmbeddedId
    private ComponenteOrdemServicoPK id = new ComponenteOrdemServicoPK();
       
    private Integer quantidade;
    
    public ComponenteOrdemServico() {
    }

    public ComponenteOrdemServico(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ComponenteOrdemServicoPK getId() {
        return id;
    }

    public void setId(ComponenteOrdemServicoPK id) {
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
        final ComponenteOrdemServico other = (ComponenteOrdemServico) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}
