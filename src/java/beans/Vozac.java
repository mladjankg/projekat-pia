/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mlađan
 */
@Entity(name = "vozaci")
public class Vozac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;
    
    @Column(name = "datum_rodjenja")
    private Date datumRodjenja;
    
    @Column(name = "pocetak_voznje")
    private Date pocetakVoznje;

    public Vozac() {
    }

    public Vozac(String ime, String prezime, Date datumRodjenja, Date pocetakVoznje) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pocetakVoznje = pocetakVoznje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Date getPocetakVoznje() {
        return pocetakVoznje;
    }

    public void setPocetakVoznje(Date pocetakVoznje) {
        this.pocetakVoznje = pocetakVoznje;
    }
    
    public int getGodineIskustva() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTime(pocetakVoznje);
        int year = calendar.get(Calendar.YEAR);
        
        return currentYear - year;
    }
}
