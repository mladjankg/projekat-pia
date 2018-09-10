/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


/**
 *
 * @author Mlađan
 */
@Entity(name = "korisnici")
public class Korisnik implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;
    
    
    @Column(name = "korisnicko_ime", unique = true)
    private String korisnickoIme;
    private String lozinka;
    
    @Transient
    private String potvrdaLozinke;
    private String adresa;
    
    @Column(name = "datum_rodjenja")
    private Date datumRodjenja;
    private String telefon;
    

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "kategorija_zaposlenja")
    
    private KategorijaZaposlenja kategorijaZaposlenja;
    
    private String mejl;
    private boolean admin;
    
    @Column(name = "admin_potvrdio")
    private boolean adminPotvrdio;
    
    @Column(name = "korisnik_validan")
    private boolean korisnikValidan;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "korisnik")
    private List<Poruka> poruke = new ArrayList<Poruka>();
    
    @Transient
    private Karta gradskaKarta;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik")
    private List<Karta> karte = new ArrayList<Karta>();
    
    public Korisnik() {
        poruke = new ArrayList<Poruka>();
        kategorijaZaposlenja = new KategorijaZaposlenja();
    }

    public Korisnik(Integer id, String ime, String prezime, String korisnickoIme, String lozinka, String potvrdaLozinke, String adresa, Date datumRodjenja, String telefon, KategorijaZaposlenja kategorijaZaposlenja, String mejl, boolean admin, boolean adminPotvrdio, boolean korisnikValidan, Karta gradskaKarta) {
        this.id = id;
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
        this.adminPotvrdio = adminPotvrdio;
        this.korisnikValidan = korisnikValidan;
        this.gradskaKarta = gradskaKarta;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

    public boolean isAdminPotvrdio() {
        return adminPotvrdio;
    }

    public void setAdminPotvrdio(boolean adminPotvrdio) {
        this.adminPotvrdio = adminPotvrdio;
    }

    public boolean isKorisnikValidan() {
        return korisnikValidan;
    }

    public void setKorisnikValidan(boolean korisnikValidan) {
        this.korisnikValidan = korisnikValidan;
    }


    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(ArrayList<Poruka> poruke) {
        this.poruke = poruke;
    }

    public List<Karta> getKarte() {
        return karte;
    }

    public void setKarte(List<Karta> karte) {
        this.karte = karte;
    }

    public Karta getGradskaKarta() {
        return gradskaKarta;
    }

    public void setGradskaKarta(Karta gradskaKarta) {
        this.gradskaKarta = gradskaKarta;
    }
    
    
}
