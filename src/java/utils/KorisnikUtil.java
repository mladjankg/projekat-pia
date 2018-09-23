/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package utils;

import beans.Karta;
import beans.PolazakMedjugradska;
import beans.managers.BeanManager;
import controllers.AccountController;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mlađan
 */
public class KorisnikUtil {

    private Date vremeCitanjaKarata = null;
    //private List<Karta> karte = null;

    public void ucitajKarte(Integer korisnikId) {
        Calendar c = Calendar.getInstance();
      //  karte = BeanManager.getList("from karte where korisnik_id=" + korisnikId);
        vremeCitanjaKarata = c.getTime();
    }
    
    public boolean korisnikRezervisao(PolazakMedjugradska p, AccountController controller, List<Karta> karte) {
       
       /* Calendar c = Calendar.getInstance();
        if (vremeCitanjaKarata == null || karte == null) {
            karte = BeanManager.getList("from karte where korisnik_id=" + korisnikId);
            vremeCitanjaKarata = c.getTime();
        } else {
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 3);

            if (c.getTime().after(vremeCitanjaKarata)) {
                karte = BeanManager.getList("from karte where korisnik_id=" + korisnikId);
                c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 3);
                vremeCitanjaKarata = c.getTime();
            }
        }*/
        
        for (Karta k : karte) {
            if (k.getPolazak() != null) {
                if (k.getPolazak().getId().equals(p.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void rezervisiKartu(PolazakMedjugradska p, AccountController controller) {
        if (p.getPreostaloMesta() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nema slobodnih mesta"));
            return;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 60);
        if (p.getVremePolaska().before(c.getTime())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Rezervacije su zatvorene"));
            p.setOtvoreneRezervacije(false);
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
            p.setOtvoreneRezervacije(p.getPreostaloMesta() > 0);
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
        
      //  this.ucitajKarte(controller.getKorisnik().getId());
    }
}
