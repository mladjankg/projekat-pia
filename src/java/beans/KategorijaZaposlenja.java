/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package beans;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Mlađan
 */
@Entity(name = "kategorije_zaposlenja")
public class KategorijaZaposlenja {
    @Id
    private String naziv;
    private int cenaKarte;
    private boolean tip; //0 - mesecna, 1 - godisnja karta
    public KategorijaZaposlenja() {
    }

    public KategorijaZaposlenja(String naziv, int cenaKarte, boolean tip) {
        this.naziv = naziv;
        this.cenaKarte = cenaKarte;
        this.tip = tip;
    }

    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCenaKarte() {
        return cenaKarte;
    }

    public void setCenaKarte(int cenaKarte) {
        this.cenaKarte = cenaKarte;
    }

    public boolean isTip() {
        return tip;
    }

    public void setTip(boolean tip) {
        this.tip = tip;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final KategorijaZaposlenja other = (KategorijaZaposlenja) obj;
        if (this.cenaKarte != other.cenaKarte) {
            return false;
        }
        if (this.tip != other.tip) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return true;
    }
}
