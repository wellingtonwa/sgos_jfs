/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.OrdemServico;

/**
 *
 * @author wellington
 */
public class OrdemServicoDao {
    
    public void save(OrdemServico ordemServico){
        try {
            if(ordemServico.getId() == 0){
                ordemServico.setDataCriacao(new Date());
                Dao.getSession().save(ordemServico);
            }
            else{
                ordemServico.setDataAlteracao(new Date());
                Dao.getSession().merge(ordemServico);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(OrdemServico ordemServico){
        try {
            Dao.getSession().delete(ordemServico);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public OrdemServico getRegistro(long id){
        return (OrdemServico) Dao.getSession().load(OrdemServico.class, id);
    }
    
    public List<OrdemServico> getRegistros(){
        return Dao.getSession().createQuery("from OrdemServico").list();
    }
}
