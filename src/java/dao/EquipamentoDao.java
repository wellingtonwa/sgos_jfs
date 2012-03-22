/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Equipamento;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class EquipamentoDao {
 
    public void save(Equipamento equipamento){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            if(equipamento.getId() == 0)
                Dao.getSession().save(equipamento);
            else
                Dao.getSession().update(equipamento);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Equipamento equipamento){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            Dao.getSession().delete(equipamento);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Equipamento getRegistro(long id){
        return (Equipamento) Dao.getSession().load(Equipamento.class, id);
    }
    
    public List<Equipamento> getRegistros(){
        return Dao.getSession().createQuery("from Equipamento").list();
    }
    
    public List<Equipamento> getEquipamentoByCliente(long idCliente){
        Query query = Dao.getSession().createQuery("from Equipamento e WHERE e.cliente.id = :idCliente");
        query.setLong("idCliente", idCliente);
        return query.list();
    }
}
