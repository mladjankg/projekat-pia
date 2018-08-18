/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package beans;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Mlađan
 */
@Entity(name = "karte")
public class Karta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "linija_id")
    private MedjugradskaLinija linija;

    private Boolean odobrena;
    
    @Column(name = "datum_rezervacije")
    private Date datumRezervacije;
    
    @Column(name = "admin_potvrdio")
    private Boolean adminPotvrdio;
    
    public Karta() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public MedjugradskaLinija getLinija() {
        return linija;
    }

    public void setLinija(MedjugradskaLinija linija) {
        this.linija = linija;
    }

    public Boolean getOdobrena() {
        return odobrena;
    }

    public void setOdobrena(Boolean odobrena) {
        this.odobrena = odobrena;
    }

    public Boolean getAdminPotvrdio() {
        return adminPotvrdio;
    }

    public void setAdminPotvrdio(Boolean adminPotvrdio) {
        this.adminPotvrdio = adminPotvrdio;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    
}
