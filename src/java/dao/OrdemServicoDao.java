/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.OrdemServico;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class OrdemServicoDao {
    
    public void save(OrdemServico ordemServico){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            if(ordemServico.getId() == 0){
                ordemServico.setDataCriacao(new Date());
                Dao.getSession().save(ordemServico);
            }
            else{
                ordemServico.setDataAlteracao(new Date());
                Dao.getSession().update(ordemServico);
            }
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(OrdemServico ordemServico){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            Dao.getSession().delete(ordemServico);
            t.commit();
        } catch (Exception e) {
            t.rollback();
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
