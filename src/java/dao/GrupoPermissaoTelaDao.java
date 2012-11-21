/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.GrupoPermissaoTela;

/**
 *
 * @author wellington
 */
public class GrupoPermissaoTelaDao {
    
    public void delete(GrupoPermissaoTela grupoPermissaoTela){
        try {
            Dao.getSession().delete(grupoPermissaoTela);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
