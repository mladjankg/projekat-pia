/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

/**
 *
 * @author Mlađan
 */
@MappedSuperclass
public abstract class Linija {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polazna_stanica_id")
    private Stanica polaznaStanica;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "odredisna_stanica_id")
    private Stanica odredisnaStanica;
    
    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn
    private List<Stanica> medjustanice;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn
    private List<Polazak> redVoznje;
    
    public Linija() {
        medjustanice = new ArrayList<Stanica>();
        redVoznje = new ArrayList<Polazak>();
        
    }

    public Linija(Stanica polaznaStanica, Stanica odredisnaStanica, List<Stanica> medjustanice, List<Polazak> redVoznje) {
        this.polaznaStanica = polaznaStanica;
        this.odredisnaStanica = odredisnaStanica;
        this.medjustanice = medjustanice;
        this.redVoznje = redVoznje;
    }

    
    
    public Stanica getPolaznaStanica() {
        return polaznaStanica;
    }

    public void setPolaznaStanica(Stanica polaznaStanica) {
        this.polaznaStanica = polaznaStanica;
    }

    public Stanica getOdredisnaStanica() {
        return odredisnaStanica;
    }

    public void setOdredisnaStanica(Stanica odredisnaStanica) {
        this.odredisnaStanica = odredisnaStanica;
    }

    public List<Stanica> getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(List<Stanica> medjustanice) {
        this.medjustanice = medjustanice;
    }

    public List<Polazak> getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(List<Polazak> redVoznje) {
        this.redVoznje = redVoznje;
    }
    
    
    
}
