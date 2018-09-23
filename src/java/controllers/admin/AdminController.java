package controllers.admin;

import beans.GradskaLinija;
import beans.Karta;
import beans.Korisnik;
import beans.MedjugradskaLinija;
import beans.Poruka;
import beans.Vozac;
import beans.managers.BeanManager;
import beans.managers.KorisnikManager;
import controllers.AccountController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
public class AdminController implements Serializable {

    private List<Korisnik> zahteviZaRegistraciju;
    private List<Karta> zahteviZaRezervaciju;
    private List<Karta> zahteviZaKupovinu;
    
    private Vozac noviVozac;
    private List<Vozac> vozaci;
    
    private GradskaLinija novaGradskaLinija;
    private List<GradskaLinija> gradskeLinije;
    
    private MedjugradskaLinija medjugradskaLinijaPrikazDetalja;
    
    private int aktivanTab = 0;

    @ManagedProperty(value = "#{accountController}")
    private AccountController accountController;

    public AdminController() {
        zahteviZaRegistraciju = new ArrayList<Korisnik>();
        zahteviZaRezervaciju = new ArrayList<>();
        zahteviZaKupovinu = new ArrayList<>();
        vozaci = new ArrayList<>();
        noviVozac = new Vozac();
    }

    @PostConstruct
    public void init() {
        this.zahteviZaRezervaciju = BeanManager.getList("from karte where admin_potvrdio=false and tip=true");
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

    public List<Karta> getZahteviZaKupovinu() {
        return zahteviZaKupovinu;
    }

    public void setZahteviZaKupovinu(List<Karta> zahteviZaKupovinu) {
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
        //Poruka p = new Poruka("Administrator je odbio vaš zahtev za registraciju.", Calendar.getInstance().getTime(), k);
        //BeanManager.addBean(p);
        //KorisnikManager.updateKorisnik(k);
        BeanManager.deleteBean(Korisnik.class, k.getId());
        return null;
    }

    private void getZahteveZaRezervaciju() {
        List<Karta> karte = (List<Karta>) BeanManager.getList("from karte where admin_potvrdio = false and tip=1");
        this.zahteviZaRezervaciju = karte;
    }

    private void getZahteveZaKupovinu() {
        List<Karta> karte = (List<Karta>) BeanManager.getList("from karte where admin_potvrdio = false and tip=0");
        this.zahteviZaKupovinu = karte;
    }
    
    
    public String obradiZahtevZaKupovinuKarte(Karta k, boolean ishod) {
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

    public String dodajVozaca() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (noviVozac == null) {
            context.addMessage(null, new FacesMessage("Unsuccessfull", "Nije dodat vozac"));
            return null;
        }
        
        BeanManager.addBean(noviVozac);
        vozaci.add(noviVozac);
        noviVozac = new Vozac();
        
        return null;
    }
    
    public int getAktivanTab() {
        return aktivanTab;
    }

    public void setAktivanTab(int aktivanTab) {
        this.aktivanTab = aktivanTab;
    }

    public Vozac getNoviVozac() {
        return noviVozac;
    }

    public void setNoviVozac(Vozac noviVozac) {
        this.noviVozac = noviVozac;
    }

    public List<Vozac> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<Vozac> vozaci) {
        this.vozaci = vozaci;
    }

    public GradskaLinija getNovaGradskaLinija() {
        return novaGradskaLinija;
    }

    public void setNovaGradskaLinija(GradskaLinija novaGradskaLinija) {
        this.novaGradskaLinija = novaGradskaLinija;
    }

    public List<GradskaLinija> getGradskeLinije() {
        return gradskeLinije;
    }

    public void setGradskeLinije(List<GradskaLinija> gradskeLinije) {
        this.gradskeLinije = gradskeLinije;
    }
    
    
}
