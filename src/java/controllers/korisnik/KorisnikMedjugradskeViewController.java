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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
@ManagedBean(name = "korisnikMedjugradskeView")
@SessionScoped
public class KorisnikMedjugradskeViewController {

    @ManagedProperty(value = "#{accountController}")
    private AccountController controller;

    private List<PolazakMedjugradska> polasci;
    private List<PolazakMedjugradska> polasciFilter;

    private PolazakMedjugradska detalji;
    private MapModel mapModel;

    public KorisnikMedjugradskeViewController() {
        polasci = new ArrayList<>();
        detalji = null;
    }

    @PreDestroy
    public void destroy() {

    }

    @PostConstruct
    public void init() {
//        List<Object> l = new ArrayList<>();
//        l.add(Calendar.getInstance().getTime());
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        Integer beanId = null;
        try {
            //TODO:zavrsi ovo sutra;
            tx = session.beginTransaction();
            Query hqlQuery = session.createQuery("from medjugradske_polasci where vreme_polaska > ?1");
            hqlQuery.setParameter(1, Calendar.getInstance().getTime());
            polasci = hqlQuery.getResultList();
             
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 45);
            Date limit = c.getTime();
            polasci.forEach(p -> {
                if (!p.getVremePolaska().before(limit)) {
                    p.setOtvoreneRezervacije(false);
                } else {
                    p.setOtvoreneRezervacije(true);
                }
                
                //TODO: Vidi da li je korisnik rezervisao kartu

            });
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Greska u komunikaciji sa bazom"));

        } finally {
            session.close();
        }
    }

    public List<PolazakMedjugradska> getPolasci() {
        return polasci;
    }

    public void setPolasci(List<PolazakMedjugradska> polasci) {
        this.polasci = polasci;
    }

    public List<PolazakMedjugradska> getPolasciFilter() {
        return polasciFilter;
    }

    public void setPolasciFilter(List<PolazakMedjugradska> polasciFilter) {
        this.polasciFilter = polasciFilter;
    }

    public PolazakMedjugradska getDetalji() {
        return detalji;
    }

    public void setDetalji(PolazakMedjugradska detalji) {
        this.detalji = detalji;
    }

    public String prikazDetalja(PolazakMedjugradska p) {
        this.mapModel = new DefaultMapModel();
        if (p == null) {
            return null;
        } else {
            MedjugradskaLinija linija = p.getMedjugradskaLinija();
            this.detalji = p;
            LatLng pocetna = new LatLng(linija.getPolaznaStanica().getLatitude(), linija.getPolaznaStanica().getLongitude());
            LatLng krajnja = new LatLng(linija.getOdredisnaStanica().getLatitude(), linija.getOdredisnaStanica().getLongitude());
            Marker m = new Marker(pocetna, linija.getPolaznaStanica().getNaziv(), null, "images/blue-pin.png");

            this.mapModel.addOverlay(new Marker(pocetna, linija.getPolaznaStanica().getNaziv(), null, "./resources/blue-pin.png"));
            this.mapModel.addOverlay(new Marker(krajnja, linija.getOdredisnaStanica().getNaziv(), null, "C:\\Users\\Mlađan\\Documents\\NetBeansProjects\\ProjekatPIA\\web\\resources\\images\\green-pin.png"));

            for (Stanica s : linija.getMedjustanice()) {
                LatLng koordinate = new LatLng(s.getLatitude(), s.getLongitude());
                this.mapModel.addOverlay(new Marker(koordinate, s.getNaziv()/*, null, "yellow-icon.png"*/));
            }
            return "detalji?faces-redirect=true";
        }
    }

    public MapModel getMapModel() {
        return this.mapModel;
    }

    public AccountController getController() {
        return controller;
    }

    public void setController(AccountController controller) {
        this.controller = controller;
    }

    public void rezervisiKartu(PolazakMedjugradska p) {
        if (p.getPreostaloMesta() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nema slobodnih mesta"));
            return;
        }

        Karta k = new Karta(controller.getKorisnik(), Calendar.getInstance().getTime(), Boolean.TRUE);
        p.setPreostaloMesta(p.getPreostaloMesta() - 1);

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        Integer beanId = null;

        try {
            tx = session.beginTransaction();
            beanId = (Integer) session.save(k);
            session.update(p);
            p.setKorisnikRezervisao(true);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Greska u komunikaciji sa bazom"));

        } finally {
            session.close();
        }
    }
}
