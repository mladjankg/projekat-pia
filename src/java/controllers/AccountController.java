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
    
    public AccountController() {
        korisnik = null;
        noviKorisnik = new Korisnik();
        username = null;
        password = null;
        String path = new File(".").getAbsolutePath();


    }
    
    public String login() {
        
        username = password = null;
        noviKorisnik = null;
        return null;
    }
    
    public String logout() {
        return null;
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
