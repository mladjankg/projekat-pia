/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.KategorijaZaposlenja;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 *
 * @author Mlađan
 */
@Entity(name = "korisnici")
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;
    
    @Column(name = "korisnicko_ime")
    private String korisnickoIme;
    private String lozinka;
    
    @Transient
    private String potvrdaLozinke;
    private String adresa;
    
    @Column(name = "datum_rodjenja")
    private Date datumRodjenja;
    private String telefon;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "kategorija_zaposlenja")
    private KategorijaZaposlenja kategorijaZaposlenja;
    private String mejl;
    private Boolean admin;

    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String korisnickoIme, String lozinka, String potvrdaLozinke, String adresa, Date datumRodjenja, String telefon, KategorijaZaposlenja kategorijaZaposlenja, String mejl, boolean admin) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.potvrdaLozinke = potvrdaLozinke;
        this.adresa = adresa;
        this.datumRodjenja = datumRodjenja;
        this.telefon = telefon;
        this.kategorijaZaposlenja = kategorijaZaposlenja;
        this.mejl = mejl;
        this.admin = admin;
    }

    public Korisnik(String ime, String prezime, String korisnickoIme, String lozinka, String adresa, Date datumRodjenja, String telefon, KategorijaZaposlenja kategorijaZaposlenja, String mejl, Boolean admin) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.adresa = adresa;
        this.datumRodjenja = datumRodjenja;
        this.telefon = telefon;
        this.kategorijaZaposlenja = kategorijaZaposlenja;
        this.mejl = mejl;
        this.admin = admin;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public KategorijaZaposlenja getKategorijaZaposlenja() {
        return kategorijaZaposlenja;
    }

    public void setKategorijaZaposlenja(KategorijaZaposlenja kategorijaZaposlenja) {
        this.kategorijaZaposlenja = kategorijaZaposlenja;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

   
    
    
    
}
