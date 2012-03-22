/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author wellington
 */
@Entity
@Table(name="ordem_servico")
public class OrdemServico implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_criacao")
    private Date dataCriacao;
    @Column(name="descricao_problema")
    private String descricaoProblema;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_alteracao")
    private Date dataAlteracao;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Equipamento equipamento;
    
    @OneToMany(mappedBy = "id.ordemServico", cascade= CascadeType.ALL)
    private List<ComponenteOrdemServico> componentes;
    
    @OneToMany(mappedBy="id.ordemServico", cascade= CascadeType.ALL)
    private List<ServicoOrdemServico> servicos;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrdemServico() {
    }

    public List<ComponenteOrdemServico> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<ComponenteOrdemServico> componentes) {
        this.componentes = componentes;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ServicoOrdemServico> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoOrdemServico> servicos) {
        this.servicos = servicos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
           
}
