/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class UsuarioDao {
    
    
    public void save(Usuario usuario){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            if(usuario.getId() == 0)
                Dao.getSession().save(usuario);
            else
                Dao.getSession().update(usuario);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
        
    }
    
    public void delete(Usuario usuario){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            Dao.getSession().delete(usuario);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Usuario getRegistro(long id){
        return (Usuario) Dao.getSession().load(Usuario.class, id);
    }
    
    public List<Usuario> getRegistros(){
        return Dao.getSession().createQuery("from Usuario").list();
    }
}
