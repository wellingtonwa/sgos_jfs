/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import model.TipoEquipamento;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class TipoEquipamentoDao {
    
    public void save(TipoEquipamento tipoEquipamento){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            if(tipoEquipamento.getId() == 0)
                Dao.getSession().save(tipoEquipamento);
            else
                Dao.getSession().update(tipoEquipamento);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(TipoEquipamento tipoEquipamento){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            Dao.getSession().delete(tipoEquipamento);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public TipoEquipamento getRegistro(long id){
        return (TipoEquipamento) Dao.getSession().load(TipoEquipamento.class, id);
    }
    
    public List<TipoEquipamento> getRegistros(){
        return Dao.getSession().createQuery("from TipoEquipamento").list();
    }
    
}
