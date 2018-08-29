/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import beans.Karta;
import beans.managers.BeanManager;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "korisnikViewController")
@ViewScoped
public class KorisnikViewController {
    @ManagedProperty(value = "#{accountController}")
    private AccountController accountController;
    
    public KorisnikViewController() {
        
    }

    public AccountController getAccountController() {
        return accountController;
    }

    public void setAccountController(AccountController accountController) {
        this.accountController = accountController;
    }
    
    public void naruciGradskuKartu() {
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        Karta k = new Karta(accountController.getKorisnik(), today, false);
        
        accountController.getKorisnik().setGradskaKarta(k);
        BeanManager.addBean(k);
    }
    
}
