/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Grupo;


/**
 *
 * @author wellington
 */
public class GrupoDao {
    
    public void save(Grupo grupo){
        try {
            if(grupo.getId() == 0){
                Dao.getSession().save(grupo);
            }
            else{
                Dao.getSession().merge(grupo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Grupo grupo){
        try {
            Dao.getSession().delete(grupo);
        } catch (Exception e) {
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
