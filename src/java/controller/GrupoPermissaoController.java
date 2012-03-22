/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GrupoDao;
import dao.PermissaoDao;
import dao.TelaDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.*;
import org.primefaces.model.DualListModel;

/**
 *
 * @author wellington
 */
@ManagedBean(name="GrupoPermissaoController")
@SessionScoped
public class GrupoPermissaoController {
    
    private Grupo grupo;
    private DataModel dataModel;
    
    private DualListModel telaDualListaModel;
    private DualListModel permissaoDualListModel;
    private DataModel dataModelPermissoes;
    
    private GrupoPermissaoTela[] permissoesAdicionadasSelecionadas;
    
    public GrupoPermissaoController() {
        dataModel = new ListDataModel(new GrupoDao().getRegistros());
        grupo = new Grupo();
        dataModelPermissoes = new ListDataModel(new Grupo().getPermissoesGrupo());
        telaDualListaModel = new DualListModel(new TelaDao().getRegistros(), new ArrayList<Tela>());
        permissaoDualListModel = new DualListModel(new PermissaoDao().getRegistros(), new ArrayList<Permissao>());
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public DataModel getDataModel() {
        List<Grupo> listGrupo = new GrupoDao().getRegistros();
        if(dataModel.getRowCount() != listGrupo.size())
            dataModel = new ListDataModel(listGrupo);
        
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public DualListModel getTelaDualListaModel() {
        return telaDualListaModel;
    }

    public void setTelaDualListaModel(DualListModel telaDualListaModel) {
        this.telaDualListaModel = telaDualListaModel;
    }
    
    public void atualizarLista(){
        dataModel = new ListDataModel(new GrupoDao().getRegistros());
    }

    public DualListModel getPermissaoDualListModel() {
        return permissaoDualListModel;
    }

    public void setPermissaoDualListModel(DualListModel permissaoDualListModel) {
        this.permissaoDualListModel = permissaoDualListModel;
    }

    public DataModel getDataModelPermissoes() {
        return dataModelPermissoes;
    }

    public void setDataModelPermissoes(DataModel dataModelPermissoes) {
        this.dataModelPermissoes = dataModelPermissoes;
    }

    public GrupoPermissaoTela[] getPermissoesAdicionadasSelecionadas() {
        return permissoesAdicionadasSelecionadas;
    }

    public void setPermissoesAdicionadasSelecionadas(GrupoPermissaoTela[] permissoesAdicionadasSelecionadas) {
        this.permissoesAdicionadasSelecionadas = permissoesAdicionadasSelecionadas;
    }
    
    /* METODOS PARA INTERAÇÃO COM A INTERFACE */
    
    public void prepararEditar(){
        grupo = (Grupo) dataModel.getRowData();
        
        telaDualListaModel = new DualListModel(new TelaDao().getRegistros(), new ArrayList<Tela>());
        permissaoDualListModel = new DualListModel(new PermissaoDao().getRegistros(), new ArrayList<Permissao>());
        
        dataModelPermissoes = new ListDataModel(grupo.getPermissoesGrupo());
    }
    
    public void adicionarPermissoes(){
        List<Tela> listTelaTarget = telaDualListaModel.getTarget();
        List<Permissao> listPermissaoTarget = permissaoDualListModel.getTarget();
        
        List<GrupoPermissaoTela> listaPermissaoTela = new ArrayList<GrupoPermissaoTela>();
        
        for(Tela tela : listTelaTarget){
            
            for(Permissao permissao : listPermissaoTarget){
                GrupoPermissaoTelaPK grupoPermissaoTelaPK = new GrupoPermissaoTelaPK();
                
                grupoPermissaoTelaPK.setGrupo(grupo);
                grupoPermissaoTelaPK.setPermissao(permissao);
                grupoPermissaoTelaPK.setTela(tela);
                
                
                GrupoPermissaoTela grupoPermissaoTela = new GrupoPermissaoTela();
                grupoPermissaoTela.setId(grupoPermissaoTelaPK);
                
                for(GrupoPermissaoTela grupoPermissaoTelaCheck : grupo.getPermissoesGrupo()){
                    if(grupoPermissaoTelaCheck.getId().getPermissao().getId() != grupoPermissaoTela.getId().getPermissao().getId()
                            && grupoPermissaoTelaCheck.getId().getTela().getId() != grupoPermissaoTela.getId().getTela().getId())
                        listaPermissaoTela.add(grupoPermissaoTela);
                    
                }
            }
        }
        
        for(GrupoPermissaoTela grupoPermissaoTelaCheck : grupo.getPermissoesGrupo()){
            listaPermissaoTela.add(grupoPermissaoTelaCheck);
        }
        
        dataModelPermissoes = new ListDataModel(listaPermissaoTela);
        
    }
    
    public void removerPermissao(){
        //GrupoPermissaoTela grupoPermissaoTela = (GrupoPermissaoTela) dataModelPermissoes.getRowData();
        
        List<GrupoPermissaoTela> listaPermissaoTela = new ArrayList<GrupoPermissaoTela>();
        
        List<Integer>  listIdRemover = new ArrayList<Integer>();
        
        
        for(int i = 0; i < dataModelPermissoes.getRowCount(); i++){
            dataModelPermissoes.setRowIndex(i);
            GrupoPermissaoTela gpt = (GrupoPermissaoTela) dataModelPermissoes.getRowData();
            listaPermissaoTela.add(gpt);
        }
        
        for(GrupoPermissaoTela grupoPermissaoTela : permissoesAdicionadasSelecionadas){
            for(int i = 0; i < listaPermissaoTela.size(); i++){
                GrupoPermissaoTela gpt = listaPermissaoTela.get(i);
                if(gpt.getId().getPermissao().getId() == grupoPermissaoTela.getId().getPermissao().getId()
                        && gpt.getId().getTela().getId() == grupoPermissaoTela.getId().getTela().getId()){
                    listaPermissaoTela.remove(i);
                    break;
                }
            }
        }
        
        dataModelPermissoes = new ListDataModel(listaPermissaoTela);
        
    }

    
    public void save(){
        List<GrupoPermissaoTela> listaPermissaoTela = new ArrayList<GrupoPermissaoTela>();
        for(int i = 0; i < dataModelPermissoes.getRowCount(); i++){
            dataModelPermissoes.setRowIndex(i);
            GrupoPermissaoTela gpt = (GrupoPermissaoTela) dataModelPermissoes.getRowData();
            listaPermissaoTela.add(gpt);
        }
        grupo.setPermissoesGrupo(listaPermissaoTela);
        new GrupoDao().save(grupo);
    }
}
