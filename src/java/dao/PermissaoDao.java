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
        try {
            if(permissao.getId()==0)
                Dao.getSession().save(permissao);
            else
                Dao.getSession().merge(permissao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Permissao permissao){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            Dao.getSession().delete(permissao);
        } catch (Exception e) {
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
