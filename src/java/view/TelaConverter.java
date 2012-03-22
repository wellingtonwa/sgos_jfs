/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.TelaDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Tela;

/**
 *
 * @author wellington
 */
@FacesConverter(value="TelaConverter")
public class TelaConverter implements Converter{
    
    public static List<Tela> telaDB;

    static {
        telaDB = new TelaDao().getRegistros();
    }
    
    public static List<Tela> atualizarLista(){
        telaDB = new TelaDao().getRegistros();
        return telaDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(value);  
  
                for (Tela tela : telaDB) {  
                    if (tela.getId() == number) {  
                        return tela;  
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
            return String.valueOf(((Tela) value).getId());  
        }  
    }
    
}
