/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class ClienteDao {
 
    public void save(Cliente cliente){
        Transaction t = Dao.getSession().beginTransaction();
        
        System.out.println("\n\n\n Salvando o Cliente... "+cliente.getNome()+"\n\n\n");
        
        try {
            if(cliente.getId() == 0)
                Dao.getSession().save(cliente);
            else
                Dao.getSession().update(cliente);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Cliente cliente){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            Dao.getSession().delete(cliente);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Cliente getRegistro(long id){
        return (Cliente) Dao.getSession().load(Cliente.class, id);
    }
    
    public List<Cliente> getRegistros(){
        return Dao.getSession().createQuery("from Cliente").list();
    }
}
