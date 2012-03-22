/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.PermissaoDao;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Permissao;

/**
 *
 * @author wellington
 */
@FacesConverter(value="PermissaoConverter")
public class PermissaoConverter implements Converter{

    public static List<Permissao> permissaoDB; 
    
    static{
        permissaoDB = atualizarLista();
    }
    
    public static List<Permissao> atualizarLista(){
        permissaoDB = new PermissaoDao().getRegistros();        
        return permissaoDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.trim().equals(""))
            return null;
        else{
            int idRegistro = Integer.parseInt(value);
            
            for(Permissao permissao : permissaoDB){
                if(permissao.getId() == idRegistro)
                    return permissao;
            }
        }
        
        return null;                    
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null || value.equals(""))
            return "";
        else
            return String.valueOf(((Permissao) value).getId());
    }
    
}
