/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.GrupoDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Equipamento;
import model.Grupo;

/**
 *
 * @author wellington
 */
@FacesConverter(value="GrupoConverter")
public class GrupoConverter implements Converter{

    public static List<Grupo> grupoDB;
    
    static{
        grupoDB = new GrupoDao().getRegistros();
    }
    
    public static List<Grupo> atualizarLista(){
        grupoDB = new GrupoDao().getRegistros();
        return grupoDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(value);  
  
                for (Grupo grupo : grupoDB) {  
                    if (grupo.getId() == number) {  
                        return grupo;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Não um é um grupo válido"));  
            }  
        }  
  
        return null; 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Grupo) value).getId());
        }
    }
    
    
}
