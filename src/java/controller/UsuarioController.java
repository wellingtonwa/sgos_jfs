/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wellington
 */
@ManagedBean(name="UsuarioController")
@SessionScoped
public class UsuarioController implements Serializable{
    
    private Usuario current;
    private DataModel dataModel;

    public UsuarioController() {
        current = new Usuario();
        dataModel = new ListDataModel(new UsuarioDao().getRegistros());
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    /* METODOS ADICIONADOS PARA INTERAÇÃO COM A INTERFACE */
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new UsuarioDao().getRegistros());
    }
    
    public void prepararAdicionar(){
        current = new Usuario();
        RequestContext.getCurrentInstance().execute("usuarioPanel.show()");
    }
    
    public void save(){
        new UsuarioDao().save(current);
        atualizarLista();
        RequestContext.getCurrentInstance().execute("usuarioPanel.close()");
    }
    
    public void prepararEditar(){
        current = (Usuario) dataModel.getRowData();
        RequestContext.getCurrentInstance().execute("usuarioPanel.show()");
    }
    
    public void editar(){
        this.save();
        atualizarLista();
    }
    
    public void delete(){
        current = (Usuario) dataModel.getRowData();
        new UsuarioDao().delete(current);
        atualizarLista();
    }
}
