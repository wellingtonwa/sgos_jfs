/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Cliente;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wellington
 */
@ManagedBean(name="ClienteController")
@SessionScoped
public class ClienteController implements Serializable{
    
    private Cliente current;
    private DataModel dataModel;

    public ClienteController() {
        current = new Cliente();
        dataModel = new ListDataModel(new ClienteDao().getRegistros());
    }

    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    
    }
    public void atulizarLista(){
        dataModel = new ListDataModel(new ClienteDao().getRegistros());
    }
    
    /* METODOS PARA INTERAÇÃO COM INTERFACE */
    public void prepararAdicionar(){
        current = new Cliente();
        RequestContext.getCurrentInstance().execute("clientePanel.show()");
    }
    
    public void save(){
        new ClienteDao().save(current);
        RequestContext.getCurrentInstance().execute("clientePanel.close()");
        atulizarLista();
    }
    
    public void prepararEditar(){
        current = (Cliente) dataModel.getRowData();
        RequestContext.getCurrentInstance().scrollTo("insertEditForm");
    }
    
    public void editar(){
        this.save();
    }
    
    public void cancelar(){
        System.out.println("\n\n\n cancelando a alteração/inserção \n\n\n");
        current = new Cliente();
    }
    
    public void delete(){
        current = (Cliente) dataModel.getRowData();
        new ClienteDao().delete(current);
        atulizarLista();
    }
}
