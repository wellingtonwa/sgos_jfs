/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author wellington
 */
@Entity
public class Componente implements Serializable{
    
   
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String nome;
    private String descricao;
    private Double preco;
    
    @ManyToMany(fetch= FetchType.LAZY)
    private List<TipoEquipamento> tiposEquipamentos;
    
    @OneToMany(mappedBy = "id.componente")
    private List<ComponenteOrdemServico> ordensServicos;

    public Componente() {
    }

    public Componente(long id, String nome, String descricao, Double preco, List<TipoEquipamento> tiposEquipamentos, List<ComponenteOrdemServico> ordensServicos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tiposEquipamentos = tiposEquipamentos;
        this.ordensServicos = ordensServicos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public List<ComponenteOrdemServico> getOrdensServicos() {
        return ordensServicos;
    }

    public void setOrdensServicos(List<ComponenteOrdemServico> ordensServicos) {
        this.ordensServicos = ordensServicos;
    }

    public Double getPreco() {
        return preco;
    }

    public String getPrecoFormatado(){
        return NumberFormat.getCurrencyInstance().format(this.preco);
    }
    
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<TipoEquipamento> getTiposEquipamentos() {
        return tiposEquipamentos;
    }

    public void setTiposEquipamentos(List<TipoEquipamento> tiposEquipamentos) {
        this.tiposEquipamentos = tiposEquipamentos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Componente other = (Componente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
}
