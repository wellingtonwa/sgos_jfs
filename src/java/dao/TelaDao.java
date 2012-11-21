/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Tela;

/**
 *
 * @author wellington
 */
public class TelaDao {

    public void save(Tela tela){
        try {
            if(tela.getId() == 0)
                Dao.getSession().save(tela);
            else
                Dao.getSession().merge(tela);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Tela tela){
        try {
            Dao.getSession().delete(tela);
        } catch (Exception e) {
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
