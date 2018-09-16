/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.GradskaLinija;
import beans.Stanica;
import beans.managers.BeanManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import utils.ApplicationUtils;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "korisnikGradskeView")
@ViewScoped
public class KorisnikGradskeViewController implements Serializable {

    private List<GradskaLinija> linije;

    private List<GradskaLinija> filtriraneLinije;

    private List<GradskaLinija> pretragaLinija;
    
    private String nazivOdredista;

    public KorisnikGradskeViewController() {
        linije = new ArrayList<>();
        filtriraneLinije = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdt.format(d);
        BeanManager.executeUpdate("update gradske_linije set aktivna=true  where CAST(otkazana_do as date) < '" + date + "'", null);
        linije = BeanManager.getList("from gradske_linije where aktivna = true order by broj_linije");
    }

    public List<GradskaLinija> getLinije() {
        return linije;
    }

    public void setLinije(List<GradskaLinija> linije) {
        this.linije = linije;
    }

    public List<GradskaLinija> getFiltriraneLinije() {
        return filtriraneLinije;
    }

    public void setFiltriraneLinije(List<GradskaLinija> filtriraneLinije) {
        this.filtriraneLinije = filtriraneLinije;
    }

    public String getNazivOdredista() {
        return nazivOdredista;
    }

    public void setNazivOdredista(String nazivOdredista) {
        this.nazivOdredista = nazivOdredista;
    }

    public void pretragaPoOdredistu() {
        //linije = BeanManager.getList("from gradske_linije where aktivna = true order by broj_linije");
        
        if (ApplicationUtils.isNullOrEmpty(nazivOdredista)) {
            this.pretragaLinija = new ArrayList<>();
            this.pretragaLinija.addAll(this.linije);
            return;
        }
        
        String nazivLower = nazivOdredista.toLowerCase();
        this.pretragaLinija = linije.stream()
                .filter((l) -> {
                    String lower = l.getOdredisnaStanica().getNaziv().toLowerCase();
                    if (!ApplicationUtils.isNullOrEmpty(lower)
                            && (nazivLower.equals(lower) || lower.contains(nazivLower.subSequence(0, nazivLower.length())))) {
                        return true;
                    }

                    if (ApplicationUtils.isNullOrEmpty(l.getMedjustanice())) {
                        return false;
                    }

                    boolean found = false;

                    for (Stanica m : l.getMedjustanice()) {
                        lower = m.getNaziv().toLowerCase();
                        if (!ApplicationUtils.isNullOrEmpty(lower) && (lower.equals(nazivLower) || lower.contains(nazivLower.subSequence(0, nazivLower.length())))) {
                            found = true;
                            break;
                        }
                    }

                    return found;
                })
                .collect(toList());
        
    }

    public List<GradskaLinija> getPretragaLinija() {
        return pretragaLinija;
    }

    public void setPretragaLinija(List<GradskaLinija> pretragaLinija) {
        this.pretragaLinija = pretragaLinija;
    }

    
    
}
