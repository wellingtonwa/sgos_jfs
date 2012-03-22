/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Componente;
import org.hibernate.Transaction;


/**
 *
 * @author wellington
 */
public class ComponenteDao {
    
    public void save(Componente componente){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            if(componente.getId() == 0)
                Dao.getSession().save(componente);
            else
                Dao.getSession().update(componente);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Componente componente){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            Dao.getSession().delete(componente);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Componente getRegistro(long id){
        return (Componente) Dao.getSession().load(Componente.class, id);
    }
    
    public List<Componente> getRegistros(){
        return Dao.getSession().createQuery("from Componente").list();
    }
}
