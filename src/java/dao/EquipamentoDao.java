/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Equipamento;
import org.hibernate.Query;

/**
 *
 * @author wellington
 */
public class EquipamentoDao {
 
    public void save(Equipamento equipamento){
        
        try {
            if(equipamento.getId() == 0)
                Dao.getSession().save(equipamento);
            else
                Dao.getSession().merge(equipamento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Equipamento equipamento){
        
        try {
            Dao.getSession().delete(equipamento);
        } catch (Exception e) {
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
