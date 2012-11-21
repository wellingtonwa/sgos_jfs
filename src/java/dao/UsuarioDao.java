/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;
import org.hibernate.Query;

/**
 *
 * @author wellington
 */
public class UsuarioDao {
    
    
    public void save(Usuario usuario){
        
        try {
            if(usuario.getId() == 0)
                Dao.getSession().save(usuario);
            else
                Dao.getSession().merge(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void delete(Usuario usuario){
        
        try {
            Dao.getSession().delete(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Usuario getRegistro(long id){
        return (Usuario) Dao.getSession().load(Usuario.class, id);
    }
    
    public List<Usuario> getRegistros(){
        return Dao.getSession().createQuery("from Usuario").list();
    }
    
    public Usuario getUsuarioByLoginAndPassword(String login, String senha){
        Query query = Dao.getSession().createQuery("from Usuario u WHERE u.login = :login AND senha = :senha");
        
        query.setString("login", login);
        query.setString("senha", senha);
        
        return (Usuario) query.uniqueResult();
    }
    
    public Usuario getUsuarioByEmailAndPassword(String email, String senha){
        Query query = Dao.getSession().createQuery("from Usuario u WHERE u.email = :email AND senha = :senha");
        
        query.setString("email", email);
        query.setString("senha", senha);
        
        return (Usuario) query.uniqueResult();
    }
}
