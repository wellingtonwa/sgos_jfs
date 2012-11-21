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
        
        try {
            if(tipoEquipamento.getId() == 0)
                Dao.getSession().save(tipoEquipamento);
            else
                Dao.getSession().merge(tipoEquipamento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(TipoEquipamento tipoEquipamento){
        
        try {
            Dao.getSession().delete(tipoEquipamento);
        } catch (Exception e) {
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
