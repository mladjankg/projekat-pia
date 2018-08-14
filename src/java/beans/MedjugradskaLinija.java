/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Mlađan
 */
@Entity(name = "medjugradska_linija")
public class MedjugradskaLinija extends Linija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prevoznik_id")
    private Prevoznik prevoznik;
    private Date vremePolaska;
    private Date vremeDolaska;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autobus_id")
    private Autobus autobus;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vozac_id")
    private Vozac vozac;

    public MedjugradskaLinija() {
    }

    public MedjugradskaLinija(Prevoznik prevoznik, Date vremePolaska, Date vremeDolaska, Autobus autobus, Vozac vozac, 
            Stanica polaznaStanica, Stanica odredisnaStanica, List<Stanica> medjustanice, List<Polazak> redVoznje) {
        super(polaznaStanica, odredisnaStanica, medjustanice, redVoznje);
        this.prevoznik = prevoznik;
        this.vremePolaska = vremePolaska;
        this.vremeDolaska = vremeDolaska;
        this.autobus = autobus;
        this.vozac = vozac;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prevoznik getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(Prevoznik prevoznik) {
        this.prevoznik = prevoznik;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public Date getVremeDolaska() {
        return vremeDolaska;
    }

    public void setVremeDolaska(Date vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }
    
    
}
