/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.GrupoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Grupo;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wellington
 */
@ManagedBean(name="GrupoController")
@SessionScoped
public class GrupoController implements Serializable{
    
    private Grupo current;
    private DataModel dataModel;

    public GrupoController() {
    }

    public Grupo getCurrent() {
        return current;
    }

    public void setCurrent(Grupo current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new GrupoDao().getRegistros());
    }
    
    /* METODOS PARA INTERAÇÃO COM INTERFACE */
    
    public void prepararAdicionar(){
        current = new Grupo();
        RequestContext.getCurrentInstance().execute("grupoPanel.show()");
    }
    
    public void save(){
        new GrupoDao().save(current);
        atualizarLista();
        RequestContext.getCurrentInstance().execute("grupoPanel.close()");
    }
    
    public void prepararEditar(){
        current = (Grupo) dataModel.getRowData();
        RequestContext.getCurrentInstance().execute("grupoPanel.show()");
    }
    
    public void delete(){
        new GrupoDao().delete(current);
        atualizarLista();
    }
        
}
