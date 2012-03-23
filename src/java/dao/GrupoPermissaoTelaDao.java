/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.GrupoPermissaoTela;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class GrupoPermissaoTelaDao {
    
    public void delete(GrupoPermissaoTela grupoPermissaoTela){
        Transaction t = Dao.getSession().beginTransaction();
        try {
            Dao.getSession().delete(grupoPermissaoTela);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
}
