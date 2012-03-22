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
@Table(name="tipo_equipamento")
public class TipoEquipamento implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String nome;
    private String descricao;
    
    @OneToMany(mappedBy="tipoEquipamento")
    private List<Equipamento> equipamentos;
    
    @ManyToMany(mappedBy = "tiposEquipamentos")
    @JoinTable(name="tipo_equipamento_has_servicos", joinColumns={@JoinColumn(name="id_tipo_equipamento", nullable=false, updatable=false)}
          , inverseJoinColumns={@JoinColumn(name="id_servico", nullable=false, updatable=false)})
    private List<Servico> servicos;
    
    @ManyToMany(mappedBy = "tiposEquipamentos")
    @JoinTable(name="tipo_equipmento_has_componente", joinColumns={@JoinColumn(name="id_tipo_equipamento", nullable=false, updatable=false)}
            , inverseJoinColumns={@JoinColumn(name="id_componente", nullable=false, updatable=false)})
    private List<Componente> componentes;

    public TipoEquipamento() {
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
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

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
