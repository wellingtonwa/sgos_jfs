/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author wellington
 */
public class ClienteDao {
 
    public void save(Cliente cliente){
        
        System.out.println("\n\n\n Salvando o Cliente... "+cliente.getNome()+"\n\n\n");
        
        try {
            if(cliente.getId() == 0)
                Dao.getSession().save(cliente);
            else
                Dao.getSession().merge(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Cliente cliente){
        
        try {
            Dao.getSession().delete(cliente);
        } catch (Exception e) {
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
