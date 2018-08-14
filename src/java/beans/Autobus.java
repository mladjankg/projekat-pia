/*
 * Mladjan Mihajlovic
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mlađan
 */
@Entity(name = "autobusi")
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marka;
    private String model;
    
    @Column(name = "broj_mesta")
    private Integer brojMesta;
    private String slike; //Linkovi do slika su odvojeni ';'

    public Autobus() {
    }

    public Autobus(String marka, String model, Integer brojMesta, String slike) {
        this.marka = marka;
        this.model = model;
        this.brojMesta = brojMesta;
        this.slike = slike;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(Integer brojMesta) {
        this.brojMesta = brojMesta;
    }

    public String getSlike() {
        return slike;
    }

    public void setSlike(String slike) {
        this.slike = slike;
    }
    
    
}
