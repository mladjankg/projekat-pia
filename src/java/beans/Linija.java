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


    
    public Linija() {
        medjustanice = new ArrayList<Stanica>();
       
        
    }

    public Linija(Stanica polaznaStanica, Stanica odredisnaStanica, List<Stanica> medjustanice) {
        this.polaznaStanica = polaznaStanica;
        this.odredisnaStanica = odredisnaStanica;
        this.medjustanice = medjustanice;
        
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
    
    
    
}
