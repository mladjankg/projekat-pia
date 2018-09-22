package beans;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */

/**
 *
 * @author Mlađan
 */
@Entity(name = "poruke")
public class Poruka implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String poruka;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;
    
    @Column(name = "datum_poruke")
    private Date datumPoruke;

    private boolean tip;
    
    public Poruka() {
    }

    public Poruka(String poruka, Date datumPoruke, Korisnik korisnik) {
        this.poruka = poruka;
        this.datumPoruke = datumPoruke;
        this.korisnik = korisnik;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Date getDatumPoruke() {
        return datumPoruke;
    }

    public void setDatumPoruke(Date datumPoruke) {
        this.datumPoruke = datumPoruke;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public boolean getTip() {
        return tip;
    }

    public void setTip(boolean tip) {
        this.tip = tip;
    }
    
    
}
