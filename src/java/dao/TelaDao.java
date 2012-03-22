/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Tela;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class TelaDao {

    public void save(Tela tela){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            if(tela.getId() == 0)
                Dao.getSession().save(tela);
            else
                Dao.getSession().update(tela);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Tela tela){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            Dao.getSession().delete(tela);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Tela getRegistro(long id){
        return (Tela) Dao.getSession().load(Tela.class, id);
    }
    
    public List<Tela> getRegistros(){
        return Dao.getSession().createQuery("from Tela").list();
    }
}
