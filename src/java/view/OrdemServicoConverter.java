/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.OrdemServicoDao;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.OrdemServico;

/**
 *
 * @author wellington
 */
@FacesConverter(value="OrdemServicoConverter")
public class OrdemServicoConverter implements Converter{

    public static List<OrdemServico> ordemServicoDB;
    
    static {
        ordemServicoDB = new OrdemServicoDao().getRegistros();
    }
    
    public static List<OrdemServico> atualizarLista(){
        ordemServicoDB = new OrdemServicoDao().getRegistros();
        return ordemServicoDB;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
