/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.Karta;
import beans.MedjugradskaLinija;
import beans.PolazakMedjugradska;
import beans.Stanica;
import controllers.AccountController;
import java.io.Serializable;
import java.util.Calendar;
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

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "detaljiLinijeView")
@SessionScoped
public class DetaljiLinijeView implements Serializable {

    private MapModel mapModel;
    private PolazakMedjugradska p;

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
        if (p.getPreostaloMesta() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nema slobodnih mesta"));
            return;
        }

        Karta k = new Karta(controller.getKorisnik(), Calendar.getInstance().getTime(), Boolean.TRUE);
        k.setPolazak(p);
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        Integer beanId = null;

        try {
            tx = session.beginTransaction();
            beanId = (Integer) session.save(k);
            Query hqlQuery = session.createQuery("from medjugradske_polasci where id=" + p.getId());
            PolazakMedjugradska pol = (PolazakMedjugradska) hqlQuery.getSingleResult();

            pol.setPreostaloMesta(pol.getPreostaloMesta() - 1);
            session.update(pol);
            p.setPreostaloMesta(pol.getPreostaloMesta());
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Greska u komunikaciji sa bazom"));
        } catch (NoResultException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        controller.getKorisnik().getKarte().add(k);
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
            return "detalji?faces-redirect=true";
        }
    }
    
    public boolean korisnikRezervisao(PolazakMedjugradska p) {
        for(Karta k: controller.getKorisnik().getKarte()) {
            if (k.getPolazak() != null) {
                return k.getPolazak().getId().equals(p.getId());
            }
        }
        
        return false;
    }
}
