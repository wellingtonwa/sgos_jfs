/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClienteDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import model.Cliente;

/**
 *
 * @author wellington
 */
@FacesConverter(value="ClienteConverter")
public class ClienteConverter implements Converter{

    public static List<Cliente> clienteDB;
    
    static{
        clienteDB = new ClienteDao().getRegistros();
    }
    
    public static List<Cliente> atualizaLista(){
        clienteDB = new ClienteDao().getRegistros();
        return clienteDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(value);  
  
                for (Cliente cliente : clienteDB) {  
                    if (cliente.getId() == number) {  
                        return cliente;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Não um cliente valido"));  
            }  
        }  
  
        return null; 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Cliente) value).getId());  
        }  
    }

    
}
