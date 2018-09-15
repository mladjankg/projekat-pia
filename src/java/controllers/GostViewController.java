/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import beans.PolazakMedjugradska;
import beans.Prevoznik;
import beans.Stanica;
import beans.managers.BeanManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import org.apache.tomcat.jni.Stdlib;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.ApplicationUtils;
import utils.HibernateUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "gostViewController")
@ViewScoped
public class GostViewController implements Serializable {

    public class ParametriPretrage implements Serializable {

        private Prevoznik prevoznik;
        private Date vremePolaskaOd;
        private Date vremePolaskaDo;
        private Date datumPolaska;
        private Stanica polaziste;
        private Stanica odrediste;

        public Date getVremePolaskaOd() {
            return vremePolaskaOd;
        }

        public void setVremePolaskaOd(Date vremePolaskaOd) {
            this.vremePolaskaOd = vremePolaskaOd;
        }

        public Date getVremePolaskaDo() {
            return vremePolaskaDo;
        }

        public void setVremePolaskaDo(Date vremePolaskaDo) {
            this.vremePolaskaDo = vremePolaskaDo;
        }

        public Date getDatumPolaska() {
            return datumPolaska;
        }

        public void setDatumPolaska(Date datumPolaska) {
            this.datumPolaska = datumPolaska;
        }

        public Prevoznik getPrevoznik() {
            return prevoznik;
        }

        public void setPrevoznik(Prevoznik prevoznik) {
            this.prevoznik = prevoznik;
        }

        public Stanica getPolaziste() {
            return polaziste;
        }

        public void setPolaziste(Stanica polaziste) {
            this.polaziste = polaziste;
        }

        public Stanica getOdrediste() {
            return odrediste;
        }

        public void setOdrediste(Stanica odrediste) {
            this.odrediste = odrediste;
        }

    }

    public GostViewController() {
        parametri = new ParametriPretrage();

    }

    @PostConstruct
    public void init() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        List list = null;

        try {
            tx = session.beginTransaction();
            Query hqlQuery = session.createQuery("from stanice where tip_stanice = 1");
            stanice = hqlQuery.getResultList();

            hqlQuery = session.createQuery("from prevoznici");
            prevoznici = hqlQuery.getResultList();

            hqlQuery = session.createQuery("from medjugradske_polasci where vreme_polaska > ?1 order by vreme_polaska");
            hqlQuery.setParameter(1, Calendar.getInstance().getTime());

            najskorijiPolasci = hqlQuery.getResultList();
            if (najskorijiPolasci.size() > 10) {
                najskorijiPolasci = najskorijiPolasci.subList(0, 9);
            }

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

            ex.printStackTrace();
        } finally {
            session.close();
        }

        stanice.add(0, Stanica.getEmpty());
        //  prevoznici = BeanManager.getList("from prevoznici");
        prevoznici.add(0, Prevoznik.getPlaceholder());
        // najskorijiPolasci = BeanManager.getList("from medjugradske_polasci where vreme_polaska > " + sadString + " order by vreme_polaska desc").subList(0, 9);
    }

    private List<PolazakMedjugradska> rezultatPretrage;
    private List<PolazakMedjugradska> najskorijiPolasci;

    private ParametriPretrage parametri;

    private List<Stanica> stanice;
    private List<Prevoznik> prevoznici;

    public List<PolazakMedjugradska> getNajskorijiPolasci() {
        return najskorijiPolasci;
    }

    public void setNajskorijiPolasci(List<PolazakMedjugradska> najskorijiPolasci) {
        this.najskorijiPolasci = najskorijiPolasci;
    }

    public ParametriPretrage getParametri() {
        return parametri;
    }

    public void setParametri(ParametriPretrage parametri) {
        this.parametri = parametri;
    }

    public List<PolazakMedjugradska> getRezultatPretrage() {
        return rezultatPretrage;
    }

    public void setRezultatPretrage(List<PolazakMedjugradska> rezultatPretrage) {
        this.rezultatPretrage = rezultatPretrage;
    }

    public List<Stanica> getStanice() {
        return stanice;
    }

    public void setStanice(List<Stanica> stanice) {
        this.stanice = stanice;
    }

    public List<Prevoznik> getPrevoznici() {
        return prevoznici;
    }

    public void setPrevoznici(List<Prevoznik> prevoznici) {
        this.prevoznici = prevoznici;
    }

    public void pretraga() {
        if (this.parametri.odrediste == null && this.parametri.polaziste == null) {
            FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", "Potrebno je uneti makar polazište ili odredište."));
            return;
        }

        if (this.parametri.datumPolaska == null) {
            FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", "Morate uneti datum polaska autobusa."));
            return;
        }
        List<Object> params = new ArrayList<>();
        
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdt.format(this.parametri.datumPolaska);
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("from medjugradske_polasci where CAST(vreme_polaska as date) = '").append(dateString).append("'");
        
        if (this.parametri.vremePolaskaOd != null) {
            Date dateTime = mergeDateAndTime(this.parametri.datumPolaska, this.parametri.vremePolaskaOd);
            params.add(dateTime);
            queryBuilder.append(" and vreme_polaska >= ?").append(params.size());
        }

        if (this.parametri.vremePolaskaDo != null) {
            Date dateTime = mergeDateAndTime(this.parametri.datumPolaska, this.parametri.vremePolaskaDo);
            params.add(dateTime);
            queryBuilder.append(" and vreme_polaska <= ?").append(params.size());
        }
                
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        List<PolazakMedjugradska> list = null;
        try {

            tx = session.beginTransaction();
            Query hqlQuery = session.createQuery(queryBuilder.toString());
            for (int i = 0 ; i < params.size(); ++i) {
                hqlQuery.setParameter(i + 1, params.get(i));
            }

            // hqlQuery.setParameter(1, this.parametri.datumPolaska);
            list = hqlQuery.getResultList();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

            FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greška", "Greška pri uspostavljanju konekcije sa bazom podataka."));
        } finally {
            session.close();
        }

        if (ApplicationUtils.isNullOrEmpty(list)) {
            FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pretraga:", " za dati unos nije pronadjen polazak."));
            return;
        }


        if (this.parametri.polaziste != null) {
            list = list.stream()
                    .filter(p
                            -> p.getMedjugradskaLinija()
                            .getPolaznaStanica()
                            .equals(this.parametri.polaziste))
                    .collect(toList());

            if (ApplicationUtils.isNullOrEmpty(list)) {
                this.rezultatPretrage = null;
                FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pretraga:", " za dati unos nije pronadjen polazak."));
                return;
            }
        }

        if (this.parametri.odrediste != null) {
            list = list.stream()
                    .filter(p
                            -> p.getMedjugradskaLinija()
                            .getOdredisnaStanica()
                            .equals(this.parametri.odrediste))
                    .collect(toList());

            if (ApplicationUtils.isNullOrEmpty(list)) {
                this.rezultatPretrage = null;
                FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pretraga:", " za dati unos nije pronadjen polazak."));
                return;
            }
        }

        if (this.parametri.prevoznik != null) {
            list = list.stream()
                    .filter(p
                            -> this.parametri.prevoznik
                            .equals(p.getMedjugradskaLinija()
                                    .getPrevoznik()))
                    .collect(toList());

            if (ApplicationUtils.isNullOrEmpty(list)) {
                this.rezultatPretrage = null;
                FacesContext.getCurrentInstance().addMessage("pretraga_poruka", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pretraga:", " za dati unos nije pronadjen polazak."));
                return;
            }
        }


        this.rezultatPretrage = list;

    }

    private Date getTimeFromDate(Calendar c, Date d) {
        if (c == null || d == null) {
            return null;
        }

        c.setTime(d);
        c.set(Calendar.YEAR, 0);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 0);

        return c.getTime();
    }

    private Date mergeDateAndTime(Date d, Date t) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(t);
        
        c.set(Calendar.HOUR, c1.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
        
        return c.getTime();
    }
}
