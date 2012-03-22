/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.TipoEquipamentoDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Cliente;
import model.TipoEquipamento;

/**
 *
 * @author wellington
 */
@FacesConverter(value="TipoEquipamentoConverter")
public class TipoEquipamentoConverter implements Converter{

    public static List<TipoEquipamento> tipoEquipamentoDB;
    
    static{
        tipoEquipamentoDB = new TipoEquipamentoDao().getRegistros();
    }
    
    public static List<TipoEquipamento> atualizarLista(){
        tipoEquipamentoDB = new TipoEquipamentoDao().getRegistros();
        return tipoEquipamentoDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(value);  
  
                for (TipoEquipamento tipoEquipamento : tipoEquipamentoDB) {  
                    if (tipoEquipamento.getId() == number) {  
                        return tipoEquipamento;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Não é um tipo de equipamento válido"));  
            }  
        }  
  
        return null; 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((TipoEquipamento) value).getId());  
        }  
    }
    
}
