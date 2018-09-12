/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mlađan
 */
@Entity(name = "red_voznje")
public class Polazak implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vreme_polaska")
    private Date vremePolaska;

    private Boolean smer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gradska_linija_id", nullable = false)
    private GradskaLinija gradskaLinija;

    public Polazak() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public Boolean getSmer() {
        return smer;
    }

    public void setSmer(Boolean smer) {
        this.smer = smer;
    }

    public GradskaLinija getGradskaLinija() {
        return gradskaLinija;
    }

    public void setGradskaLinija(GradskaLinija gradskaLinija) {
        this.gradskaLinija = gradskaLinija;
    }

    
    
}
