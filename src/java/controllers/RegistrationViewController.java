/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import beans.KategorijaZaposlenja;
import beans.Korisnik;
import beans.managers.BeanManager;
import beans.managers.KorisnikManager;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "registrationViewController")
@ViewScoped
public class RegistrationViewController implements Serializable {

    private List<KategorijaZaposlenja> kategorije;
    private Korisnik noviKorisnik;

    public RegistrationViewController() {

    }

    @PostConstruct
    public void init() {
        kategorije = BeanManager.getList("from kategorije_zaposlenja");
        noviKorisnik = new Korisnik();
    }

    public List<KategorijaZaposlenja> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<KategorijaZaposlenja> kategorije) {
        this.kategorije = kategorije;
    }

    public Korisnik getNoviKorisnik() {
        return noviKorisnik;
    }

    public void setNoviKorisnik(Korisnik noviKorisnik) {
        this.noviKorisnik = noviKorisnik;
    }

    public String register() {
        if (KorisnikManager.getKorisnikByUsername(noviKorisnik.getKorisnickoIme()) != null) {
            FacesContext.getCurrentInstance().addMessage("validation_message", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Korisničko ime je zauzeto."));
            return null;
        }

        if (!noviKorisnik.getLozinka().equals(noviKorisnik.getPotvrdaLozinke())) {
            FacesContext.getCurrentInstance().addMessage("validation_message", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Unete lozinke se ne poklapaju."));
            return null;
        }
        
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        messageDigest.update(noviKorisnik.getLozinka().getBytes());
        String enkriptovanaLozinka = new String(messageDigest.digest());
        String encodedString = Base64.getEncoder().encodeToString(enkriptovanaLozinka.getBytes());

        noviKorisnik.setLozinka(encodedString);
        KorisnikManager.addKorisnik(noviKorisnik);
        noviKorisnik = new Korisnik();

        return "index";
    }
}
