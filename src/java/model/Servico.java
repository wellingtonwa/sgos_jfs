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
public class Servico implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String nome;
    private String descricao;
    private Double preco;
    
    @ManyToMany
    private List<TipoEquipamento> tiposEquipamentos;

    public Servico() {
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getPrecoFormatado(){
        return NumberFormat.getCurrencyInstance().format(this.preco);
    }
    
    public List<TipoEquipamento> getTiposEquipamentos() {
        return tiposEquipamentos;
    }

    public void setTiposEquipamentos(List<TipoEquipamento> tiposEquipamentos) {
        this.tiposEquipamentos = tiposEquipamentos;
    }
    
    
    
}
