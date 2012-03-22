/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EquipamentoDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Cliente;
import model.Equipamento;
import model.TipoEquipamento;
import org.primefaces.context.RequestContext;
import view.ClienteConverter;
import view.TipoEquipamentoConverter;

/**
 *
 * @author wellington
 */
@ManagedBean(name="EquipamentoController")
@SessionScoped
public class EquipamentoController {
    
    private Equipamento current;
    private DataModel dataModel;
    private List<Cliente> clientes;
    private List<TipoEquipamento> tiposEquipamentos;
    
    public EquipamentoController() {
        current = new Equipamento();
        dataModel = new ListDataModel(new EquipamentoDao().getRegistros());
        ClienteConverter.atualizaLista();
        clientes = ClienteConverter.clienteDB;
        TipoEquipamentoConverter.atualizarLista();
        tiposEquipamentos = TipoEquipamentoConverter.tipoEquipamentoDB;
    }

    public Equipamento getCurrent() {
        return current;
    }

    public void setCurrent(Equipamento current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new EquipamentoDao().getRegistros());
        ClienteConverter.atualizaLista();
        clientes = ClienteConverter.clienteDB; 
        TipoEquipamentoConverter.atualizarLista();
        tiposEquipamentos = TipoEquipamentoConverter.tipoEquipamentoDB;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<TipoEquipamento> getTiposEquipamentos() {
        return tiposEquipamentos;
    }

    public void setTiposEquipamentos(List<TipoEquipamento> tiposEquipamentos) {
        this.tiposEquipamentos = tiposEquipamentos;
    }
    
    
    
    /* METODO PARA INTERACAO COM A INTERFACE */
    
    public void deletar(){
        current = (Equipamento) dataModel.getRowData();
        new EquipamentoDao().delete(current);
        atualizarLista();
    }
    
    public void prepararAdicionar(){
        current = new Equipamento();
        atualizarLista();
        RequestContext.getCurrentInstance().execute("equipamentoPanel.show()");
    }
    
    public void save(){
        
        Cliente cliente = current.getCliente();
        TipoEquipamento tipoEquipamento = current.getTipoEquipamento();
        
        new EquipamentoDao().save(current);
        atualizarLista();
        RequestContext.getCurrentInstance().execute("equipamentoPanel.close()");
    }
    
    public void prepararEditar(){
        current = (Equipamento) dataModel.getRowData();
        atualizarLista();
        RequestContext.getCurrentInstance().execute("equipamentoPanel.show()");
    }
    
    public void editar(){
        this.save();
    }
}
