/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServicoDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.Servico;
import model.TipoEquipamento;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import view.TipoEquipamentoConverter;

/**
 *
 * @author wellington
 */
@ManagedBean(name="ServicoController")
@SessionScoped
public class ServicoController {
    
    private Servico current;
    private DataModel dataModel;
    private String preco;
    private DualListModel dualListModel;
    private List<TipoEquipamento> tiposEquipamentos;
    private List<TipoEquipamento> tiposEquipamentosSelecionados;

    public ServicoController() {
        current = new Servico();
        current.setTiposEquipamentos(new ArrayList<TipoEquipamento>());
        dataModel = new ListDataModel(new ServicoDao().getRegistros());
        TipoEquipamentoConverter.atualizarLista();
        dualListModel = new DualListModel(TipoEquipamentoConverter.tipoEquipamentoDB, current.getTiposEquipamentos());
    }

    public Servico getCurrent() {
        return current;
    }

    public void setCurrent(Servico current) {
        this.current = current;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new ServicoDao().getRegistros());
        TipoEquipamentoConverter.atualizarLista();
        dualListModel.setSource(TipoEquipamentoConverter.tipoEquipamentoDB);
    }

    public List<TipoEquipamento> getTiposEquipamentos() {
        return tiposEquipamentos;
    }

    public void setTiposEquipamentos(List<TipoEquipamento> tiposEquipamentos) {
        this.tiposEquipamentos = tiposEquipamentos;
    }

    public List<TipoEquipamento> getTiposEquipamentosSelecionados() {
        return tiposEquipamentosSelecionados;
    }

    public void setTiposEquipamentosSelecionados(List<TipoEquipamento> tiposEquipamentosSelecionados) {
        this.tiposEquipamentosSelecionados = tiposEquipamentosSelecionados;
    }

    public DualListModel getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel dualListModel) {
        this.dualListModel = dualListModel;
    }
    
    /* METODOS PARA INTERAÇÃO COM A INTERFACE */
    
    public void prepararAdicionar(){
        atualizarLista();
        current = new Servico();
        preco = "0";
        dualListModel.setTarget(new ArrayList<TipoEquipamento>());
        RequestContext.getCurrentInstance().execute("servicoPanel.show()");
        RequestContext.getCurrentInstance().execute("moeda()");
    }
    
    public void save(){
        
        if(preco != null && !preco.trim().equals("")){
            preco = preco.replaceAll("[Rr$ ]", "");
            preco = preco.replaceAll("[,]", ".");
        }
        current.setPreco(Double.parseDouble(preco));
        
        current.setTiposEquipamentos(dualListModel.getTarget());
        
        new ServicoDao().save(current);
        
        atualizarLista();
        
        RequestContext.getCurrentInstance().execute("servicoPanel.close()");
    }
    
    public void prepararEditar(){
        
        
        current = new ServicoDao().getRegistro(((Servico) dataModel.getRowData()).getId());
        
        atualizarLista();
        
        preco = current.getPrecoFormatado();
        
        // Preparando o PickList        
        dualListModel.setTarget(current.getTiposEquipamentos());
        
        HashMap<Long, TipoEquipamento> hashSource = new HashMap<Long, TipoEquipamento>();
        
        for(TipoEquipamento forTipoEquipamento : TipoEquipamentoConverter.tipoEquipamentoDB){
            hashSource.put(forTipoEquipamento.getId(), forTipoEquipamento);
        }
        
        for(TipoEquipamento forTipoEquipamento : current.getTiposEquipamentos()){
            hashSource.remove(forTipoEquipamento.getId());
        }
        
        List<TipoEquipamento> listSource = new ArrayList<TipoEquipamento>();
        
        for(TipoEquipamento forTipoEquipamento : hashSource.values()){
            listSource.add(forTipoEquipamento);
        }
        
        dualListModel.setSource(listSource);
        
        RequestContext.getCurrentInstance().execute("servicoPanel.show()");
        RequestContext.getCurrentInstance().execute("moeda()");
    } 
    
    public void editar(){
        this.save();
    }
    
    public void delete(){
        System.out.println("Deletando... \n\n\n");
        new ServicoDao().delete(current);
        atualizarLista();
    }
    
}
