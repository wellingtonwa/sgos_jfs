/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.EquipamentoDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Cliente;
import model.Equipamento;

/**
 *
 * @author wellington
 */
@FacesConverter(value="EquipamentoConverter")
public class EquipamentoConverter implements Converter{

    public static List<Equipamento> equipamentosDB;
    
    static {
        equipamentosDB = new EquipamentoDao().getRegistros();
    }
    
    public static List<Equipamento> atualizrLista(){
        equipamentosDB = new EquipamentoDao().getRegistros();
        return equipamentosDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(value);  
  
                for (Equipamento equipamento : equipamentosDB) {  
                    if (equipamento.getId() == number) {  
                        return equipamento;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Não um é um equipamento válido"));  
            }  
        }  
  
        return null; 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Equipamento) value).getId());  
        }
    }
    
}
