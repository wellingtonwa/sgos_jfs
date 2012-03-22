/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrdemServicoDao;
import dao.ServicoDao;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.swing.text.NumberFormatter;
import model.*;
import org.primefaces.context.RequestContext;
import view.ClienteConverter;
import view.OrdemServicoConverter;

/**
 *
 * @author wellington
 */
@ManagedBean(name="OrdemServicoController")
@SessionScoped
public class OrdemServicoController implements Serializable{
    
    private OrdemServico current;
    private DataModel dataModel;
    private List<Cliente> clientes;
    private List<Equipamento> equipamentos;
    private DataModel dataModelServicos;
    private DataModel dataModelServicosAdicionados;
    private List<ServicoOrdemServico> listServicoOrdemServico;
    private String precoServicos;
    
    public OrdemServicoController() {
        current = new OrdemServico();
        current.setCliente(new Cliente());
        clientes = ClienteConverter.atualizaLista();
        dataModel = new ListDataModel(new OrdemServicoDao().getRegistros());
        equipamentos = new ArrayList<Equipamento>();
        dataModelServicos = new ListDataModel(new ArrayList<Equipamento>());
        dataModelServicosAdicionados = new ListDataModel(new ArrayList<ServicoOrdemServico>());
    }

    public OrdemServico getCurrent() {
        return current;
    }

    public void setCurrent(OrdemServico current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(OrdemServicoConverter.atualizarLista());
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public DataModel getDataModelServicos() {
        return dataModelServicos;
    }

    public void setDataModelServicos(DataModel dataModelServicos) {
        this.dataModelServicos = dataModelServicos;
    }

    public DataModel getDataModelServicosAdicionados() {
        return dataModelServicosAdicionados;
    }

    public void setDataModelServicosAdicionados(DataModel dataModelServicosAdicionados) {
        this.dataModelServicosAdicionados = dataModelServicosAdicionados;
    }

    public List<ServicoOrdemServico> getListServicoOrdemServico() {
        return listServicoOrdemServico;
    }

    public void setListServicoOrdemServico(List<ServicoOrdemServico> listServicoOrdemServico) {
        this.listServicoOrdemServico = listServicoOrdemServico;
    }

    public String getPrecoServicos() {
        return precoServicos;
    }

    public void setPrecoServicos(String precoServicos) {
        this.precoServicos = precoServicos;
    }
            
    /* METODOS PARA INTERAGIR COM A INTERFACE */
    
    public void prepararAdicionar(){
        atualizarLista();
        listServicoOrdemServico = new ArrayList<ServicoOrdemServico>();
        dataModelServicosAdicionados = new ListDataModel(listServicoOrdemServico);
        current = new OrdemServico();
        if(clientes.size()>0){
            current.setCliente(clientes.get(0));
            this.carregarEquipamentoByCliente();
            
            if(equipamentos.size() >0){
                current.setEquipamento(equipamentos.get(0));
                this.carregarServicosByTipoEquipamento();
            }
                
        }
        RequestContext.getCurrentInstance().execute("ordemServicoPanel.show()");
    }
    
    public void save(){
        current.setServicos(listServicoOrdemServico);
        new OrdemServicoDao().save(current);
        atualizarLista();
        RequestContext.getCurrentInstance().execute("ordemServicoPanel.close()");
    }
    
    public void delete(){
        current = (OrdemServico) dataModel.getRowData();
        new OrdemServicoDao().delete(current);
        atualizarLista();
    }
    
    public void prepararEditar(){
        current = (OrdemServico) dataModel.getRowData();
        listServicoOrdemServico = current.getServicos();
        
        this.carregarServicosByTipoEquipamento();
        
        atualizarLista();
        RequestContext.getCurrentInstance().execute("ordemServicoPanel.show()");
    }
    
    public void editar(){
        this.save();
    }
    
    public void carregarEquipamentoByCliente(){
        if(current.getCliente() != null && current.getCliente().getId() != 0){
            equipamentos = current.getCliente().getEquipamentos();
            current.setEquipamento(equipamentos.get(0));
        }
        else 
            equipamentos = new ArrayList<Equipamento>();
        
        this.carregarServicosByTipoEquipamento(); 
        
    }
    
    public void carregarServicosByTipoEquipamento(){
        if(current.getEquipamento() != null && current.getEquipamento().getId() != 0)
            dataModelServicos = new ListDataModel(new ServicoDao().getServicoByTipoEquipamento(current.getEquipamento().getTipoEquipamento().getId()));
        else
            dataModelServicos = new ListDataModel(new ArrayList<Servico>());
        
        this.calcularDiferencaServicosDisponiveisServicosAdicionados();
    }
    
    public void adicionarServico(){
        Servico servico = (Servico) dataModelServicos.getRowData();
        
        boolean encontrado = false;
        
        if(listServicoOrdemServico.size() > 0){
            for(ServicoOrdemServico servicoOrdemServico : listServicoOrdemServico){
                if(servicoOrdemServico.getId().getServico().getId() == servico.getId()){
                    servicoOrdemServico.setQuantidade(servicoOrdemServico.getQuantidade()+1);
                    encontrado = true;
                    break;
                }
            }
        }
        
        if(encontrado == false){
            ServicoOrdemServico servicoOrdemServico = new ServicoOrdemServico();
            servicoOrdemServico.setQuantidade(1);
            servicoOrdemServico.setId(new ServicoOrdemServicoPK(current, servico));        
            listServicoOrdemServico.add(servicoOrdemServico);
        }
        
        dataModelServicosAdicionados = new ListDataModel(listServicoOrdemServico);
        
        this.calcularPrecos();

    }
    
    public void removerServico(){
        Servico servico = (Servico) dataModelServicos.getRowData();
        
        //System.out.println(servicoOrdemServicoDataTable.getId().getServico().getId()+"<<<\n\n\n");
        
        if(listServicoOrdemServico.size() > 0){
            for(ServicoOrdemServico servicoOrdemServico : listServicoOrdemServico){
//                System.out.println(servicoOrdemServico.getId().getServico().getId()+"<<<\n\n\n");
                if(servicoOrdemServico.getId().getServico().getId() == servico.getId()){
                    int novaQuantidade = servicoOrdemServico.getQuantidade()-1;
                    if(novaQuantidade > 0){
                        servicoOrdemServico.setQuantidade(novaQuantidade);
                    }
                    else{
                        listServicoOrdemServico.remove(servicoOrdemServico);
                    }
                    break;
                }
            }
        }
        
        this.calcularPrecos();
        
        dataModelServicosAdicionados = new ListDataModel(listServicoOrdemServico);
    }
    
    public void calcularPrecos(){
        Double valorDouble = 0.0;
        if(listServicoOrdemServico.size() > 0){
            for(ServicoOrdemServico servicoOrdemServico : listServicoOrdemServico){
                valorDouble += servicoOrdemServico.getId().getServico().getPreco() * servicoOrdemServico.getQuantidade();
            }
        }
        
        precoServicos = "R$ "+NumberFormat.getCurrencyInstance().format(valorDouble);
    }
    
    public String formatarPreco(Double preco){
        return NumberFormat.getCurrencyInstance().format(preco);
    }
    
    public void calcularDiferencaServicosDisponiveisServicosAdicionados(){
        
        List<ServicoOrdemServico> novaListaServicoOrdemServico = new ArrayList<ServicoOrdemServico>();
        
        
        for(ServicoOrdemServico servicoOrdemServico : listServicoOrdemServico){
            boolean encontrado = false;
            for(Servico servico : current.getEquipamento().getTipoEquipamento().getServicos()){
                if(servico.getId() == servicoOrdemServico.getId().getServico().getId()){
                    encontrado = true;
                    break;
                }
            }
            if(encontrado)
                novaListaServicoOrdemServico.add(servicoOrdemServico);
        }
        listServicoOrdemServico = novaListaServicoOrdemServico;
        this.calcularPrecos();
        dataModelServicosAdicionados = new ListDataModel(novaListaServicoOrdemServico);
    }
    
}
