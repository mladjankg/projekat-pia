/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.Karta;
import beans.managers.BeanManager;
import controllers.AccountController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "korisnikViewController")
@ViewScoped
public class KorisnikViewController implements Serializable {

    @ManagedProperty(value = "#{accountController}")
    private AccountController accountController;

    public KorisnikViewController() {

    }

    @PostConstruct
    public void init() {
        Date today = Calendar.getInstance().getTime();
        List<Object> l = new ArrayList<>();
        l.add(today);
        Object o = BeanManager.getObject("from karte where tip=0 and korisnik_id=" + accountController.getKorisnik().getId().toString() + " and datum_vazenja > ?1 order by datum_kupovine desc", l);
        Karta karta = null;
        if (o != null) {
            karta = (Karta) o;
        }
        accountController.getKorisnik().setGradskaKarta(karta);
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
        Karta k = new Karta(accountController.getKorisnik(), today, false, accountController.getKorisnik().getKategorijaZaposlenja().getCenaKarte());

        accountController.getKorisnik().setGradskaKarta(k);
        BeanManager.addBean(k);
    }

    public boolean isGradskaValidna(Karta k) {
        if (k == null) {
            return false;
        }

        if (k.getTip()) {
            return false;
        }

        if (k.getAdminPotvrdio() && !k.getOdobrena()) {
            return false;
        }

        if (k.getDatumVazenja() == null) {
            return true;
        }

        Date today = Calendar.getInstance().getTime();
        if (k.getDatumVazenja().before(today)) {
            return false;
        }

        return true;
    }
}
