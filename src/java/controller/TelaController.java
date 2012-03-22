/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TelaDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Tela;
import org.primefaces.context.RequestContext;


/**
 *
 * @author wellington
 */
@ManagedBean(name="TelaController")
@SessionScoped
public class TelaController implements Serializable{
    
    private Tela current;
    private DataModel dataModel;

    public TelaController() {
        current = new Tela();
        dataModel = new ListDataModel(new TelaDao().getRegistros());
    }

    public Tela getCurrent() {
        return current;
    }

    public void setCurrent(Tela current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarTabela(){
        dataModel = new ListDataModel(new TelaDao().getRegistros());
    }
    
    /* METODOS PARA INTERAÇÃO COM A INTERFACES */
    
    public void prepararAdicionar(){
        current = new Tela();
        RequestContext.getCurrentInstance().execute("telaPanel.show()");
    }
    
    public void save(){
        new TelaDao().save(current);
        atualizarTabela();
        RequestContext.getCurrentInstance().execute("telaPanel.close()");
    }
    
    public void prepararEditar(){
        current = (Tela) dataModel.getRowData();
        RequestContext.getCurrentInstance().execute("telaPanel.show()");
    }
    
    public void editar(){
        this.save();
    }
    
    public void delete(){
        current = (Tela) dataModel.getRowData();
        new TelaDao().delete(current);
        atualizarTabela();
    }
}
