/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

/**
 *
 * @author Mlađan
 */
@Entity(name = "gradske_linije")
public class GradskaLinija extends Linija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "broj_linije")
    private Integer brojLinije;
   
    @OneToMany
    private List<Vozac> vozaci;

    public GradskaLinija() {
        super();
        vozaci = new ArrayList<Vozac>();
    }

    public GradskaLinija(Integer brojLinije, List<Vozac> vozaci, Stanica polaznaStanica, Stanica odredisnaStanica, List<Stanica> medjustanice, List<Polazak> redVoznje) {
        super(polaznaStanica, odredisnaStanica, medjustanice, redVoznje);
        this.brojLinije = brojLinije;
        this.vozaci = vozaci;
    } 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrojLinije() {
        return brojLinije;
    }

    public void setBrojLinije(Integer brojLinije) {
        this.brojLinije = brojLinije;
    }

    public List<Vozac> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<Vozac> vozaci) {
        this.vozaci = vozaci;
    }
    
    
}
