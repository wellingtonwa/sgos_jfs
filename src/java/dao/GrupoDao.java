/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Grupo;
import org.hibernate.Transaction;


/**
 *
 * @author wellington
 */
public class GrupoDao {
    
    public void save(Grupo grupo){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            if(grupo.getId() == 0){
                Dao.getSession().save(grupo);
            }
            else{
                Dao.getSession().update(grupo);
            }
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Grupo grupo){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            Dao.getSession().delete(grupo);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Grupo getRegistro(long id){
        return (Grupo) Dao.getSession().load(Grupo.class, id);
    }
    
    public List<Grupo> getRegistros(){
        return Dao.getSession().createQuery("from Grupo").list();
    }
}
