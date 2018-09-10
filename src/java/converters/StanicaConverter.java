/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package converters;

import beans.KategorijaZaposlenja;
import beans.Stanica;
import beans.managers.BeanManager;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mlađan
 */
@FacesConverter("stanicaConverter")
public class StanicaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            if (string == null) {
                return null;
            }
            
            int id = Integer.parseInt(string);
            Object o = BeanManager.getObject("from stanice where id=" + id);
            if (o == null) {
                return null;
            }
            else {
                return (Stanica)o;
            }
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(string + " ne predstavlja ID stanice."), e);
        }   
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Stanica) {
            return ((Stanica) modelValue).getId().toString();
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " nije validan objekat tipa Stanica."));
        }
    }
    
}
