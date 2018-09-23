/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.Karta;
import beans.MedjugradskaLinija;
import beans.PolazakMedjugradska;
import beans.Stanica;
import beans.managers.BeanManager;
import controllers.AccountController;
import java.io.Serializable;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import utils.HibernateUtil;
import utils.KorisnikUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "detaljiLinijeView")
@SessionScoped
public class DetaljiLinijeView implements Serializable {

    private KorisnikUtil util = new KorisnikUtil();
    private MapModel mapModel;
    private PolazakMedjugradska p;
    private List<Karta> karte = new ArrayList<>();
    @ManagedProperty(value = "#{accountController}")
    private AccountController controller;

    public DetaljiLinijeView() {

    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public PolazakMedjugradska getP() {
        return p;
    }

    public void setP(PolazakMedjugradska p) {
        this.p = p;
    }

    public AccountController getController() {
        return controller;
    }

    public void setController(AccountController controller) {
        this.controller = controller;
    }

    public String nazad() {
        return "korisnik-medjugradske?faces-redirect=true";
    }

    public void rezervisiKartu() {
        KorisnikUtil util = new KorisnikUtil();
        util.rezervisiKartu(this.p, controller);
        karte = BeanManager.getList("from karte where korisnik_id =" + controller.getKorisnik().getId() );
    }

    public String prikazDetalja(PolazakMedjugradska p) {
        this.p = p;
        this.mapModel = new DefaultMapModel();
        if (p == null) {
            return null;
        } else {
            MedjugradskaLinija linija = p.getMedjugradskaLinija();
            LatLng pocetna = new LatLng(linija.getPolaznaStanica().getLatitude(), linija.getPolaznaStanica().getLongitude());
            LatLng krajnja = new LatLng(linija.getOdredisnaStanica().getLatitude(), linija.getOdredisnaStanica().getLongitude());
            Marker m = new Marker(pocetna, linija.getPolaznaStanica().getNaziv(), null, "images/blue-pin.png");

            this.mapModel.addOverlay(new Marker(pocetna, linija.getPolaznaStanica().getNaziv(), null, "resources/images/blue.png"));
            this.mapModel.addOverlay(new Marker(krajnja, linija.getOdredisnaStanica().getNaziv(), null, "resources/images/green.png"));

            for (Stanica s : linija.getMedjustanice()) {
                LatLng koordinate = new LatLng(s.getLatitude(), s.getLongitude());
                this.mapModel.addOverlay(new Marker(koordinate, s.getNaziv(), null, "resources/images/yellow.png"));
            }
            
            karte = BeanManager.getList("from karte where korisnik_id =" + controller.getKorisnik().getId() );
            return "detalji?faces-redirect=true";
        }
    }

    public boolean korisnikRezervisao(PolazakMedjugradska p) {

        return util.korisnikRezervisao(p, controller, karte);
    }
}
