/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.Poruka;
import beans.managers.BeanManager;
import controllers.AccountController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "porukeViewController")
@ViewScoped
public class PorukeViewController implements Serializable {
    
    @ManagedProperty(value = "#{accountController}")
    private AccountController controller;
    
    private List<Poruka> poruke;
    
    public PorukeViewController() {
        
    }
    
    @PostConstruct
    public void init() {
        poruke = BeanManager.getList("from poruke where korisnik_id = " + controller.getKorisnik().getId() + " or tip=true order by datum_poruke desc");
    }

    public AccountController getController() {
        return controller;
    }

    public void setController(AccountController controller) {
        this.controller = controller;
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }
    
    
    
}
