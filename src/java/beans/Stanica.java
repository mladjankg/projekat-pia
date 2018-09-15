/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mlađan
 */
@Entity(name = "stanice")
public class Stanica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    private String naziv;
    
    @Column(name = "tip_stanice")
    private Integer tipStanice;

    private Double latitude;
    private Double longitude;
    
    public Stanica() {
    }

    public Stanica(String naziv, Integer tipStanice) {
        this.naziv = naziv;
        this.tipStanice = tipStanice;
    }

    public Stanica(Integer id, String naziv, Integer tipStanice, Double latitude, Double longitude) {
        this.id = id;
        this.naziv = naziv;
        this.tipStanice = tipStanice;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Integer getTipStanice() {
        return tipStanice;
    }

    public void setTipStanice(Integer tipStanice) {
        this.tipStanice = tipStanice;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Stanica other = (Stanica) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return this.id + " " + naziv;
    }
    
    public static Stanica getEmpty() {
        Stanica s = new Stanica("", null);
        s.setId(0);
        return s;
    }
}
