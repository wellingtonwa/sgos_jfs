/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoEquipamentoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.TipoEquipamento;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wellington
 */
@ManagedBean(name="TipoEquipamentoController")
@SessionScoped
public class TipoEquipamentoController implements Serializable{
    
    private TipoEquipamento current;
    private DataModel dataModel;

    public TipoEquipamentoController() {
        current = new TipoEquipamento();
        dataModel = new ListDataModel(new TipoEquipamentoDao().getRegistros());
    }

    public TipoEquipamento getCurrent() {
        return current;
    }

    public void setCurrent(TipoEquipamento current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new TipoEquipamentoDao().getRegistros());
    }
    
    /* METODOS PARA INTERAÇÃO COM A INTERFACE*/
    
    public void prepararAdicionar(){
        current = new TipoEquipamento();
        atualizarLista();
        RequestContext.getCurrentInstance().execute("tipoEquipamentoPanel.show()");
    }
    
    public void save(){
        System.out.println("\n\n\n Salvando... "+current.getNome()+"\n\n\n");
        new TipoEquipamentoDao().save(current);
        atualizarLista();
        RequestContext.getCurrentInstance().execute("tipoEquipamentoPanel.close()");
    }
    
    public void prepararEditar(){
        current = new TipoEquipamentoDao().getRegistro(((TipoEquipamento) dataModel.getRowData()).getId());
        RequestContext.getCurrentInstance().execute("tipoEquipamentoPanel.show()");
    }
    
    public void editar(){
        this.save();
        RequestContext.getCurrentInstance().execute("tipoEquipamentoPanel.close()");
    }
    
    public void delete(){
        current = (TipoEquipamento) dataModel.getRowData();
        new TipoEquipamentoDao().delete(current);
        atualizarLista();
    }
}
