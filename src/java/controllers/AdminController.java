package controllers;


import beans.Korisnik;
import beans.Poruka;
import beans.managers.BeanManager;
import beans.managers.KorisnikManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController {
    private List<Korisnik> zahteviZaRegistraciju;
    
    public AdminController() {
        zahteviZaRegistraciju = new ArrayList<Korisnik>();
    }

    public List<Korisnik> getZahteviZaRegistraciju() {
        return zahteviZaRegistraciju;
    }

    public void setZahteviZaRegistraciju(List<Korisnik> zahteviZaRegistraciju) {
        this.zahteviZaRegistraciju = zahteviZaRegistraciju;
    }
    
    public String prikaziZahteveZaRegistraciju() {
        Korisnik k = (Korisnik) BeanManager.getBean(Korisnik.class, 3);
        this.zahteviZaRegistraciju = KorisnikManager.getZahteveZaRegistraciju();
        return "korisnici.xhtml";
    }
    
    public String odobriZahtev(Korisnik k) {
        k.setAdminPotvrdio(true);
        k.setKorisnikValidan(true);
        zahteviZaRegistraciju.remove(k);
        Poruka p = new Poruka("Administrator je odobrio vaš zahtev za registraciju.", Calendar.getInstance().getTime(), k);
        BeanManager.addBean(p);
        KorisnikManager.updateKorisnik(k);
        return null;
    }
    
    public String odbijZahtev(Korisnik k) {
        k.setAdminPotvrdio(true);
        k.setKorisnikValidan(false);
        zahteviZaRegistraciju.remove(k);
        Poruka p = new Poruka("Administrator je odbio vaš zahtev za registraciju.", Calendar.getInstance().getTime(), k);
        BeanManager.addBean(p);
        KorisnikManager.updateKorisnik(k);
        return null;
    }
}
