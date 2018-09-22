/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.korisnik;

import beans.Karta;
import beans.PolazakMedjugradska;
import beans.managers.BeanManager;
import controllers.AccountController;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "rezervacijeViewController")
@ViewScoped
public class RezervacijeViewController implements Serializable {
    @ManagedProperty(value="#{accountController}")
    private AccountController controller;
    
    private List<Karta> karte;
    
    private Date sad;
    private Date sadPlusSat;
    
    @PostConstruct
    public void init() {
        Calendar c = Calendar.getInstance();
        this.sad = c.getTime();
        karte = BeanManager.getList("from karte where tip=true and korisnik_id=" + controller.getKorisnik().getId());
        karte.stream()
                .sorted((x, y) -> x.getPolazak().getVremePolaska().compareTo(y.getPolazak().getVremePolaska()))
                .collect(toList());
        
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 1);
        sadPlusSat = c.getTime();
    }

    public AccountController getController() {
        return controller;
    }

    public void setController(AccountController controller) {
        this.controller = controller;
    }

    public List<Karta> getKarte() {
        return karte;
    }

    public void setKarte(List<Karta> karte) {
        this.karte = karte;
    }
    
     public void otkaziRezervaciju(Karta k) {
        //TODO: Ne radi brisanje
        controller.getKorisnik().getKarte().remove(k);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
         try {
            tx = session.beginTransaction();
            Query hqlQuery = session.createQuery("delete from karte where id=:idKarte");
            hqlQuery.setParameter("idKarte", k.getId());
            hqlQuery.executeUpdate();
            
            hqlQuery = session.createQuery("from medjugradske_polasci where id=:idPolaska");
            hqlQuery.setParameter("idPolaska", k.getPolazak().getId());
            PolazakMedjugradska p = (PolazakMedjugradska) hqlQuery.getSingleResult();
            
            p.setPreostaloMesta(p.getPreostaloMesta() + 1);
            session.update(p);
            k.getPolazak().setPreostaloMesta(p.getPreostaloMesta());
            karte.remove(k);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }         
        } finally {
            session.close();
        }
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Rezervacija uspešno otkazana."));
    }

    public Date getSad() {
        return sad;
    }

    public void setSad(Date sad) {
        this.sad = sad;
    }

    public Date getSadPlusSat() {
        return sadPlusSat;
    }

    public void setSadPlusSat(Date sadPlusSat) {
        this.sadPlusSat = sadPlusSat;
    }
     
     
}
