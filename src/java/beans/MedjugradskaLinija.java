/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import utils.ApplicationUtils;

/**
 *
 * @author Mlađan
 */
@Entity(name = "medjugradske_linije")
public class MedjugradskaLinija extends Linija implements Serializable {
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prevoznik_id")
    private Prevoznik prevoznik;
    
    @OneToMany(mappedBy = "medjugradskaLinija")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PolazakMedjugradska> polasci;
    
    public MedjugradskaLinija() {
    }

    @Transient
    private String color = "black";
    
    @Transient
    private String medjustaniceMerged = null;
    
    public MedjugradskaLinija(Prevoznik prevoznik, Stanica polaznaStanica, Stanica odredisnaStanica, List<Stanica> medjustanice) {
        super(polaznaStanica, odredisnaStanica, medjustanice);
        this.prevoznik = prevoznik;
       
    }

    public Prevoznik getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(Prevoznik prevoznik) {
        this.prevoznik = prevoznik;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<PolazakMedjugradska> getPolasci() {
        return polasci;
    }

    public void setPolasci(List<PolazakMedjugradska> polasci) {
        this.polasci = polasci;
    }
    
    public String getMedjustaniceMerged() {
        if ("".equals(this.medjustaniceMerged)) {
            return "";
        }
        
        if (ApplicationUtils.isNullOrEmpty(this.getMedjustanice())) {
            this.medjustaniceMerged = "";
            return this.medjustaniceMerged;
        }
        
        StringBuilder sb = new StringBuilder();
        
        this.getMedjustanice().forEach(m -> sb.append(m.getNaziv()).append(", "));
        
        sb.delete(sb.length() - 2, sb.length() - 1);
        return sb.toString();
    }
}
