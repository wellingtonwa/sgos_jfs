/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Permissao;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class PermissaoDao {
    
    public void save(Permissao permissao){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            if(permissao.getId()==0)
                Dao.getSession().save(permissao);
            else
                Dao.getSession().update(permissao);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Permissao permissao){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            Dao.getSession().delete(permissao);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Permissao getRegistro(long id){
        return (Permissao) Dao.getSession().load(Permissao.class, id);
    }
    
    public List<Permissao> getRegistros(){
        return Dao.getSession().createQuery("from Permissao").list();
    }
}
