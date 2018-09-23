/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import beans.Karta;
import beans.Korisnik;
import beans.managers.BeanManager;
import beans.managers.KorisnikManager;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utils.ApplicationUtils;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "accountController")
@SessionScoped
public class AccountController implements Serializable {

    private Korisnik korisnik;
    private String username;
    private String password;
    private String loginError;

    public AccountController() {
        korisnik = null;

        username = null;
        password = null;

    }

    public String login() {
        Korisnik k = KorisnikManager.getKorisnikByUsername(this.username);
        if (k == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška:", "Neispravno korisničko ime"));
            this.username = null;
            return null;
        }
        
        //Provera lozinke
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        messageDigest.update(this.password.getBytes());
        String encodedString = new String(messageDigest.digest());
        String enkriptovanaLozinka = Base64.getEncoder().encodeToString(encodedString.getBytes());

        if (!k.getLozinka().equals(enkriptovanaLozinka)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška:", "Neispravna lozinka"));
            this.password = null;
            return null;
        }

        korisnik = k;

        if (korisnik.isAdmin()) {
            return "zahtevi?faces-redirect=true";
        } else {
            if (!korisnik.isAdminPotvrdio()) {
                korisnik = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", "Administrator još uvek nije odobrio Vaš zahtev za registraciju."));
                return null;
            }
            
            Date d = Calendar.getInstance().getTime();
            if (!ApplicationUtils.isNullOrEmpty(korisnik.getKarte())) {
                List<Karta> karte = korisnik.getKarte()
                        .stream()
                        .filter(f -> f.getTip() && f.getPolazak().getVremePolaska().after(d))
                        .collect(toList());
                korisnik.setKarte(karte);
            }
            return "poruke?faces-redirect=true";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
        session.invalidate();
        return "index?faces-redirect=true";
    }

    public String guest() {
        return null;
    }

    public String userCheck() {
        if (korisnik == null) {
            return "index?faces-redirect=true";
        } else if (korisnik.isAdmin()) {
            return "admin/admin?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String adminCheck() {
        if (korisnik == null) {
            return "index?faces-redirect=true";
        } else if (!korisnik.isAdmin()) {
            return "korisnik?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String guestCheck() {
        if (korisnik != null) {
            if (!korisnik.isAdmin()) {
                return "korisnik?faces-redirect=true";
            } else {
                return "zahtevi?faces-redirect=true";
            }
        }
        else {
            return null;
        }
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
