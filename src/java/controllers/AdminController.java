package controllers;

import beans.Karta;
import beans.KartaGradskiPrevoz;
import beans.Korisnik;
import beans.Poruka;
import beans.managers.BeanManager;
import beans.managers.KorisnikManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private List<Karta> zahteviZaRezervaciju;
    private List<KartaGradskiPrevoz> zahteviZaKupovinu;
    
    private int aktivanTab = 0;

    @ManagedProperty(value = "#{accountController}")
    private AccountController accountController;

    public AdminController() {
        zahteviZaRegistraciju = new ArrayList<Korisnik>();
        zahteviZaRezervaciju = new ArrayList<>();
        zahteviZaKupovinu = new ArrayList<>();
    }

    public List<Korisnik> getZahteviZaRegistraciju() {
        return zahteviZaRegistraciju;
    }

    public void setZahteviZaRegistraciju(List<Korisnik> zahteviZaRegistraciju) {
        this.zahteviZaRegistraciju = zahteviZaRegistraciju;
    }

    public List<Karta> getZahteviZaRezervaciju() {
        return zahteviZaRezervaciju;
    }

    public void setZahteviZaRezervaciju(List<Karta> zahteviZaRezervaciju) {
        this.zahteviZaRezervaciju = zahteviZaRezervaciju;
    }

    public List<KartaGradskiPrevoz> getZahteviZaKupovinu() {
        return zahteviZaKupovinu;
    }

    public void setZahteviZaKupovinu(List<KartaGradskiPrevoz> zahteviZaKupovinu) {
        this.zahteviZaKupovinu = zahteviZaKupovinu;
    }

    public AccountController getAccountController() {
        return accountController;
    }

    public void setAccountController(AccountController accountController) {
        this.accountController = accountController;
    }

    public void ucitajZahteve() {
        if (accountController.getKorisnik() == null) {
            return;
        } else if (!accountController.getKorisnik().isAdmin()) {
            return;
        }

        this.zahteviZaRegistraciju = KorisnikManager.getZahteveZaRegistraciju();
        this.getZahteveZaKupovinu();
        this.getZahteveZaRezervaciju();
    }

    public String prikaziZahteveZaRegistraciju() {
        if (accountController.getKorisnik() == null) {
            return "index?faces-redirect=true";
        } else if (!accountController.getKorisnik().isAdmin()) {
            return "korisnik?faces-redirect=true";
        }

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

    private void getZahteveZaRezervaciju() {
        List<Karta> karte = (List<Karta>) BeanManager.getList("from karte where admin_potvrdio = false");
        this.zahteviZaRezervaciju = karte;
    }

    private void getZahteveZaKupovinu() {
        List<KartaGradskiPrevoz> karte = (List<KartaGradskiPrevoz>) BeanManager.getList("from gradske_karte where admin_potvrdio = false");
        this.zahteviZaKupovinu = karte;
    }
    
    public String obradiZahtevZaKupovinuKarte(KartaGradskiPrevoz k, boolean ishod) {
        k.setAdminPotvrdio(true);
        k.setOdobrena(ishod);
        BeanManager.updateBean(k);
        this.zahteviZaKupovinu.remove(k);
        return null;
    }
    
    public String obradiZahtevZaRezervacijuKarte(Karta k, boolean ishod) {
        k.setAdminPotvrdio(true);
        k.setOdobrena(ishod);
        BeanManager.updateBean(k);
        this.zahteviZaRezervaciju.remove(k);
        return null;
    } 

    public int getAktivanTab() {
        return aktivanTab;
    }

    public void setAktivanTab(int aktivanTab) {
        this.aktivanTab = aktivanTab;
    }
    
}
