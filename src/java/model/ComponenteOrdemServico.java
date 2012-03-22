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
    
}
