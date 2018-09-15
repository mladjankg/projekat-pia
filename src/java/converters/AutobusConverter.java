/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package converters;

import beans.Autobus;
import beans.Prevoznik;
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
@FacesConverter("autobusConverter")
public class AutobusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            if (string == null) {
                return null;
            }

            int id = Integer.parseInt(string);
            Object o = BeanManager.getObject("from autobusi where id=" + id);
            if (o == null) {
                return null;
            } else {
                return o;
            }
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(string + " ne predstavlja ID autobusa."), e);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Autobus) {
            return ((Autobus) modelValue).getId().toString();
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " nije validan objekat tipa Autobus."));
        }
    }
    
}
