/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.ComponenteDao;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Componente;
import model.TipoEquipamento;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import view.ComponenteConverter;
import view.TipoEquipamentoConverter;

/**
 *
 * @author wellington
 */
@ManagedBean(name="ComponenteController")
@SessionScoped
public class ComponenteController implements Serializable{

    private Componente current;
    private DataModel dataModel;
    private String preco; 
    private DualListModel dualListModel;
    
    public ComponenteController() {
        current = new Componente();
        current.setTiposEquipamentos(new ArrayList<TipoEquipamento>());
        dataModel = new ListDataModel(ComponenteConverter.atualizarLista());
        dualListModel = new DualListModel(TipoEquipamentoConverter.atualizarLista(), new ArrayList<TipoEquipamento>());
        
    }

    public Componente getCurrent() {
        return current;
    }

    public void setCurrent(Componente current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new ComponenteDao().getRegistros());
        dualListModel.setSource(TipoEquipamentoConverter.atualizarLista());
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public DualListModel getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel dualListModel) {
        this.dualListModel = dualListModel;
    }
    
    /* METODOS PARA INTERAÇÃO COM A INTERFACE */
    
    public void prepararAdicionar(){
        current = new Componente();
        preco = NumberFormat.getCurrencyInstance().format(0L);
        dualListModel.setTarget(new ArrayList<TipoEquipamento>());
        atualizarLista();
        RequestContext.getCurrentInstance().execute("componentePanel.show()");
        RequestContext.getCurrentInstance().execute("moeda()");
    }
    
    public void save(){
        if(preco != null && !preco.trim().equals("")){
            preco = preco.replaceAll("[Rr$ ]", "");
            preco = preco.replaceAll("[,]", ".");
        }
        
        current.setPreco(Double.parseDouble(preco));
        current.setTiposEquipamentos(dualListModel.getTarget());
        
        new ComponenteDao().save(current);
        atualizarLista();
        RequestContext.getCurrentInstance().execute("componentePanel.close()");
    }
    
    public void prepararEditar(){
        
        current = (Componente) dataModel.getRowData();
        preco = current.getPrecoFormatado();
        atualizarLista();
        // Preparao o PickList
        
        HashMap<Long, TipoEquipamento> sourceHash = new HashMap<Long, TipoEquipamento>();
        
        for(TipoEquipamento forTipoEquipamento : TipoEquipamentoConverter.tipoEquipamentoDB){
            sourceHash.put(forTipoEquipamento.getId(), forTipoEquipamento);
        }
        
        for(TipoEquipamento forTipoEquipamento : current.getTiposEquipamentos()){
            sourceHash.remove(forTipoEquipamento.getId());
        }
        
        ArrayList<TipoEquipamento> sourceList = new ArrayList<TipoEquipamento>();
        
        for(TipoEquipamento forTipoEquipamento : sourceHash.values()){
            sourceList.add(forTipoEquipamento);
        }
        
        dualListModel.setSource(sourceList);
        dualListModel.setTarget(current.getTiposEquipamentos());
        
        RequestContext.getCurrentInstance().execute("componentePanel.show()");
        RequestContext.getCurrentInstance().execute("moeda()");
    }
    
    public void edit(){
        this.save();
    }
    
    public void delete(){
        current = (Componente) dataModel.getRowData();
        new ComponenteDao().delete(current);
        atualizarLista();
    }
}
