/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Componente;


/**
 *
 * @author wellington
 */
public class ComponenteDao {
    
    public void save(Componente componente){
        
        try {
            if(componente.getId() == 0)
                Dao.getSession().save(componente);
            else
                Dao.getSession().merge(componente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Componente componente){
        
        try {
            Dao.getSession().delete(componente);
        } catch (Exception e) {
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
