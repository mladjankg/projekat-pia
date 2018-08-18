/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package beans;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Mlađan
 */
@Entity(name = "gradske_karte")
public class KartaGradskiPrevoz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "datum_kupovine")
    private Date datumKupovine;
    
    private Boolean odobrena;
    
    @Column(name = "admin_potvrdio")
    private Boolean adminPotvrdio;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    public KartaGradskiPrevoz() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatumKupovine() {
        return datumKupovine;
    }

    public void setDatumKupovine(Date datumKupovine) {
        this.datumKupovine = datumKupovine;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
