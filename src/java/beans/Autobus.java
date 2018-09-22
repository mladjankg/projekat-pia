/*
 * Mladjan Mihajlovic
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package beans;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import utils.ApplicationUtils;

/**
 *
 * @author Mlađan
 */
@Entity(name = "autobusi")
public class Autobus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marka;
    private String model;
    
    @Column(name = "broj_mesta")
    private Integer brojMesta;
    private String slike; //Linkovi do slika su odvojeni ';'

    @OneToMany(mappedBy = "autobus")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PolazakMedjugradska> polasci = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "prevoznik_id")
    private Prevoznik prevoznik;
    
    private String registracija;
    
    public Autobus() {
    }

    public Autobus(String marka, String model, Integer brojMesta, String slike) {
        this.marka = marka;
        this.model = model;
        this.brojMesta = brojMesta;
        this.slike = slike;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(Integer brojMesta) {
        this.brojMesta = brojMesta;
    }

    public String getSlike() {
        return slike;
    }

    public void setSlike(String slike) {
        this.slike = slike;
    }

    public List<PolazakMedjugradska> getPolasci() {
        return polasci;
    }

    public void setPolasci(List<PolazakMedjugradska> polasci) {
        this.polasci = polasci;
    }

    public Prevoznik getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(Prevoznik prevoznik) {
        this.prevoznik = prevoznik;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public List<String> getSlikeList() {
        List<String> lista = new ArrayList<>();
        
        if (ApplicationUtils.isNullOrEmpty(this.slike)) {
            return lista;
        }
        StringTokenizer st = new StringTokenizer(this.slike, ";");
        
        String basePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("upload.location") + File.separator;
        while (st.hasMoreTokens()) {
            lista.add(basePath + st.nextToken());
        }
        
        return lista;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Autobus other = (Autobus) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.prevoznik, other.prevoznik)) {
            return false;
        }
        return true;
    }
    
    
    
}
