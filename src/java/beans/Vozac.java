/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mlađan
 */
@Entity(name = "vozaci")
public class Vozac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;
    
    @Column(name = "datum_rodjenja")
    private Date datumRodjenja;
    
    @Column(name = "pocetak_voznje")
    private Date pocetakVoznje;

    private Integer tip; // 0 - vozi gradske linije, 1 - vozi medjugradske linije
    
    @ManyToOne
    @JoinColumn(name = "gradska_linija_id")
    private GradskaLinija gradskaLinija;
       
    public Vozac() {
    }

    public Vozac(String ime, String prezime, Date datumRodjenja, Date pocetakVoznje, Integer tip) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pocetakVoznje = pocetakVoznje;
        this.tip = tip;
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

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public GradskaLinija getGradskaLinija() {
        return gradskaLinija;
    }

    public void setGradskaLinija(GradskaLinija gradskaLinija) {
        this.gradskaLinija = gradskaLinija;
    }
    
    
    public int getGodineIskustva() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTime(pocetakVoznje);
        int year = calendar.get(Calendar.YEAR);
        
        return currentYear - year;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vozac other = (Vozac) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
