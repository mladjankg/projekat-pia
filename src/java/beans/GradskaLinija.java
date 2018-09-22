/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import utils.ApplicationUtils;

/**
 *
 * @author Mlađan
 */
@Entity(name = "gradske_linije")
public class GradskaLinija extends Linija implements Serializable {

    @Column(name = "broj_linije", unique = true)
    private Integer brojLinije;

    @OneToMany(mappedBy = "gradskaLinija")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Polazak> redVoznje;

    private Boolean aktivna;

    @Column(name = "otkazana_do")
    private Date otkazanaDo;

    @Transient
    private String otkazanaDoString;

    @OneToMany(mappedBy = "gradskaLinija")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Vozac> vozaci;

    public GradskaLinija() {
        super();
        vozaci = new ArrayList<>();
        redVoznje = new ArrayList<>();
        aktivna = true;
    }

    public GradskaLinija(Integer brojLinije, List<Vozac> vozaci, Stanica polaznaStanica, Stanica odredisnaStanica, List<Stanica> medjustanice, List<Polazak> redVoznje) {
        super(polaznaStanica, odredisnaStanica, medjustanice);
        this.brojLinije = brojLinije;
        this.vozaci = vozaci;
        this.redVoznje = redVoznje;
        this.aktivna = true;
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

    public List<Polazak> getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(List<Polazak> redVoznje) {
        if (!ApplicationUtils.isNullOrEmpty(redVoznje)) {
            this.redVoznje = redVoznje.stream().sorted((x, y) -> x.getVremePolaska().compareTo(y.getVremePolaska())).collect(toList());
        }
        else {
            this.redVoznje = redVoznje;
        }
    }

    public Boolean getAktivna() {
        return aktivna;
    }

    public void setAktivna(Boolean aktivna) {
        this.aktivna = aktivna;
    }

    public Date getOtkazanaDo() {
        return otkazanaDo;
    }

    public void setOtkazanaDo(Date otkazanaDo) {
        this.otkazanaDo = otkazanaDo;
    }

    public String getOtkazanaDoString() {
        if (this.otkazanaDo == null) {
            return null;
        }

        SimpleDateFormat std = new SimpleDateFormat("dd-MM-yyyy");
        return std.format(otkazanaDo);

    }

    public void setOtkazanaDoString(String otkazanaDoString) {
        this.otkazanaDoString = otkazanaDoString;
    }

}
