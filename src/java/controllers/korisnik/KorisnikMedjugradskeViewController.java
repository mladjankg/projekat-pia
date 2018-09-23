/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.Karta;
import beans.Korisnik;
import beans.MedjugradskaLinija;
import beans.PolazakMedjugradska;
import beans.Stanica;
import beans.managers.BeanManager;
import controllers.AccountController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import utils.KorisnikUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "korisnikMedjugradskeView")
@ViewScoped
public class KorisnikMedjugradskeViewController implements Serializable {

    private KorisnikUtil util = new KorisnikUtil();
    
    @ManagedProperty(value = "#{accountController}")
    private AccountController controller;

    private List<PolazakMedjugradska> polasci;
    private List<PolazakMedjugradska> polasciFilter;
    private List<Karta> karte;
    
    private PolazakMedjugradska detalji;

    public KorisnikMedjugradskeViewController() {
        polasci = new ArrayList<>();
        detalji = null;
    }

    @PreDestroy
    public void destroy() {

    }

    @PostConstruct
    public void init() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        Integer beanId = null;
        try {
            
            tx = session.beginTransaction();
            Query hqlQuery = session.createQuery("from medjugradske_polasci where vreme_polaska > ?1");
            hqlQuery.setParameter(1, Calendar.getInstance().getTime());
            polasci = hqlQuery.getResultList();

            Calendar c = Calendar.getInstance();
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 60);
            Date limit = c.getTime();
            polasci.forEach(p -> {
                if (!limit.before(p.getVremePolaska())) {
                    p.setOtvoreneRezervacije(false);
                } else {
                    p.setOtvoreneRezervacije(p.getPreostaloMesta() > 0);
                }
            });
            
            hqlQuery = session.createQuery("from karte where korisnik_id =" + controller.getKorisnik().getId() );
            karte = hqlQuery.getResultList();
            
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

    public AccountController getController() {
        return controller;
    }

    public void setController(AccountController controller) {
        this.controller = controller;
    }

    public void rezervisiKartu(PolazakMedjugradska p) {
        KorisnikUtil util = new KorisnikUtil();
        util.rezervisiKartu(p, controller);
        karte = BeanManager.getList("from karte where korisnik_id =" + controller.getKorisnik().getId() );
    }

    public void rezervisiKartuDetalji() {
        this.rezervisiKartu(this.detalji);
    }

    public boolean korisnikRezervisao(PolazakMedjugradska p) {

        return util.korisnikRezervisao(p, controller, karte);

    }

}
