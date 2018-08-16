/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.TipStanice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mlađan
 */
@Entity(name = "stanice")
public class Stanica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    private String naziv;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tip_stanice")
    private TipStanice tipStanice;

    private double latitude;
    private double longitude;
    
    public Stanica() {
    }

    public Stanica(String naziv, TipStanice tipStanice) {
        this.naziv = naziv;
        this.tipStanice = tipStanice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TipStanice getTipStanice() {
        return tipStanice;
    }

    public void setTipStanice(TipStanice tipStanice) {
        this.tipStanice = tipStanice;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
}
