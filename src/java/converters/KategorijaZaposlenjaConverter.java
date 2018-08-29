/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package converters;

import beans.KategorijaZaposlenja;
import beans.managers.BeanManager;
import controllers.RegistrationViewController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
/**
 *
 * @author Mlađan
 */
@FacesConverter("kategorijaZaposlenjaConverter")
public class KategorijaZaposlenjaConverter implements Converter {

    @ManagedProperty(value="registrationViewController")
    private RegistrationViewController registrationViewController;
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof KategorijaZaposlenja) {
            return ((KategorijaZaposlenja) modelValue).getNaziv();
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid KategorijaZaposlenja object."));
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
//            for(KategorijaZaposlenja kz:registrationViewController.getKategorije()) {
//                if (kz.getNaziv().equals(submittedValue)) {
//                    return kz;
//                }
//            }
//            return null;
            
            Object o = BeanManager.getObject("from kategorije_zaposlenja where naziv='" + submittedValue + "'");
            if (o == null) {
                return null;
            }
            else {
                return (KategorijaZaposlenja)o;
            }
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
        }
    }

    public RegistrationViewController getRegistrationViewController() {
        return registrationViewController;
    }

    public void setRegistrationViewController(RegistrationViewController registrationViewController) {
        this.registrationViewController = registrationViewController;
    }

    
    
}
