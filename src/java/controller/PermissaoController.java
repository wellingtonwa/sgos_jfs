/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PermissaoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Permissao;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wellington
 */
@ManagedBean(name="PermissaoController")
@SessionScoped
public class PermissaoController implements Serializable{
    
    private Permissao current;
    private DataModel dataModel;

    public PermissaoController() {
        current = new Permissao();
        dataModel = new ListDataModel(new PermissaoDao().getRegistros());
    }

    public Permissao getCurrent() {
        return current;
    }

    public void setCurrent(Permissao current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new PermissaoDao().getRegistros());
    }
    
    /* METODOS PARA INTERAÇÃO COM A INTERFACES */
    
    public void prepararAdicionar(){
        current = new Permissao();
        RequestContext.getCurrentInstance().execute("permissaoPanel.show()");
    }
    
    public void save(){
        new PermissaoDao().save(current);
        this.atualizarLista();
        RequestContext.getCurrentInstance().execute("permissaoPanel.close()");
    }
    
    public void prepararEditar(){
        current = (Permissao) dataModel.getRowData();
        RequestContext.getCurrentInstance().execute("permissaoPanel.show()");
    }
    
    public void editar(){
        this.save();
    }
    
    public void delete(){
        current = (Permissao) dataModel.getRowData();
        new PermissaoDao().delete(current);
        this.atualizarLista();
    }
    
}
