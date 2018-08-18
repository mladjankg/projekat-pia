/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import beans.Korisnik;
import beans.managers.KorisnikManager;
import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "accountController")
@SessionScoped
public class AccountController {

    private Korisnik korisnik;
    private Korisnik noviKorisnik;
    private String username;
    private String password;
    private String loginError;

    public AccountController() {
        korisnik = null;
        noviKorisnik = new Korisnik();
        username = null;
        password = null;

    }

    public String login() {
        Korisnik k = KorisnikManager.getKorisnikByUsername(this.username);
        if (k == null) {
            loginError = "Neispravno korisnicko ime i/ili lozinka.";
            return null;
        }

        if (!k.getLozinka().equals(this.password)) {
            loginError = "Neispravno korisnicko ime i/ili lozinka.";
            return null;
        }

        korisnik = k;

        if (korisnik.isAdmin()) {
            return "admin?faces-redirect=true";
        } else {
            return "korisnik?faces-redirect=true";
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

    public String register() {
        if (KorisnikManager.getKorisnikByUsername(noviKorisnik.getKorisnickoIme()) != null) {
            return null;
        }

        KorisnikManager.addKorisnik(noviKorisnik);
        noviKorisnik = new Korisnik();

        return "index";
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
                return "korisnik?faces-redirect=true";
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

    public Korisnik getNoviKorisnik() {
        return noviKorisnik;
    }

    public void setNoviKorisnik(Korisnik novKorisnik) {
        this.noviKorisnik = novKorisnik;
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
