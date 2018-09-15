/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import beans.KategorijaZaposlenja;
import beans.managers.BeanManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "registrationViewController")
@ViewScoped
public class RegistrationViewController implements Serializable {
    private List<KategorijaZaposlenja> kategorije;
    
    public RegistrationViewController() {
        kategorije = BeanManager.getList("from kategorije_zaposlenja");
    }

    public List<KategorijaZaposlenja> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<KategorijaZaposlenja> kategorije) {
        this.kategorije = kategorije;
    }
    
    
}
