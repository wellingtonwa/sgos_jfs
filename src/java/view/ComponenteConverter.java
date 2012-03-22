/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ComponenteDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Componente;
import model.TipoEquipamento;

/**
 *
 * @author wellington
 */
@FacesConverter(value="ComponenteConverter")
public class ComponenteConverter implements Converter{
    
    public static List<Componente> componenteDB;
    
    static {
        componenteDB = new ComponenteDao().getRegistros();
    }
    
    public static List<Componente> atualizarLista(){
        componenteDB = new ComponenteDao().getRegistros();
        return componenteDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(value);  
  
                for (Componente componene : componenteDB) {  
                    if (componene.getId() == number) {  
                        return componene;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Não é um component válido"));  
            }  
        }  
  
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Componente) value).getId());  
        }  
    }
    
}
