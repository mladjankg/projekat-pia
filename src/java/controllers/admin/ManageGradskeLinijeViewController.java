/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.admin;

import beans.GradskaLinija;
import beans.Polazak;
import beans.Stanica;
import beans.Vozac;
import beans.managers.BeanManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import utils.HibernateUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "gradskeViewController")
@ViewScoped
public class ManageGradskeLinijeViewController implements Serializable {

    private String activeTab;

    private List<Vozac> vozaci;
    private List<GradskaLinija> linije;
    private List<GradskaLinija> neotkazaneLinije;
    private List<Stanica> stanice;

    private Date periodOtkazivanja;
    private Integer brojOtkazaneLinije;

    private Vozac noviVozac = new Vozac();

    private final Date maxDatumRodjenjaVozaca;
    private Date minDatumPocetakVoznje;
    private final Date sutra;
    private final Date danas;

    private GradskaLinija novaLinija = new GradskaLinija();

    private Stanica polStanica;

    private Date vremePolaska;

    private DualListModel<Stanica> medjustanice;

    public ManageGradskeLinijeViewController() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        danas = c.getTime();

        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 24); //Vozac mora imati makar 24 godine da bi mogao da dobije dozvolu za voznju autobusa
        maxDatumRodjenjaVozaca = c.getTime();

        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 26);
        minDatumPocetakVoznje = c.getTime();

        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 50);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        sutra = c.getTime();

        activeTab = "0";
    }

    @PostConstruct
    public void init() {
        vozaci = BeanManager.getList("from vozaci where gradska_linija_id is null and tip=0");
        linije = BeanManager.getList("from gradske_linije");

        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 23, 59);
        Date d = c.getTime();
        List<GradskaLinija> aktivne = new ArrayList<>();
        for (GradskaLinija l : linije) {
            if (l.getOtkazanaDo() == null || l.getAktivna()) {
                continue;
            }

            if (d.compareTo(l.getOtkazanaDo()) >= 0) {
                l.setAktivna(Boolean.TRUE);
                aktivne.add(l);
            }
        }

        if (aktivne.size() > 0) {
            BeanManager.updateList(aktivne);
        }

        stanice = BeanManager.getList("from stanice where tip_stanice = 0");
        neotkazaneLinije = linije.stream().filter(f -> f.getAktivna()).collect(toList());

        medjustanice = new DualListModel<>(stanice, new ArrayList<>());
    }

    public List<Vozac> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<Vozac> vozaci) {
        this.vozaci = vozaci;
    }

    public List<GradskaLinija> getLinije() {
        return linije;
    }

    public void setLinije(List<GradskaLinija> linije) {
        this.linije = linije;
    }

    public List<Stanica> getStanice() {
        return stanice;
    }

    public void setStanice(List<Stanica> stanice) {
        this.stanice = stanice;
    }

    public Date getPeriodOtkazivanja() {
        return periodOtkazivanja;
    }

    public void setPeriodOtkazivanja(Date periodOtkazivanja) {
        this.periodOtkazivanja = periodOtkazivanja;
    }

    public Integer getBrojOtkazaneLinije() {
        return brojOtkazaneLinije;
    }

    public void setBrojOtkazaneLinije(Integer brojOtkazaneLinije) {
        this.brojOtkazaneLinije = brojOtkazaneLinije;
    }

    public Vozac getNoviVozac() {
        return noviVozac;
    }

    public void setNoviVozac(Vozac noviVozac) {
        this.noviVozac = noviVozac;
    }

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }

    public Stanica getPolStanica() {
        return polStanica;
    }

    public void setPolStanica(Stanica polStanica) {
        this.polStanica = polStanica;
    }

    public List<GradskaLinija> getNeotkazaneLinije() {
        return neotkazaneLinije;
    }

    public void setNeotkazaneLinije(List<GradskaLinija> neotkazaneLinije) {
        this.neotkazaneLinije = neotkazaneLinije;
    }

    public GradskaLinija getNovaLinija() {
        return novaLinija;
    }

    public void setNovaLinija(GradskaLinija novaLinija) {
        this.novaLinija = novaLinija;
    }

    public Date getSutra() {
        return sutra;
    }

    public Date getMaxDatumRodjenjaVozaca() {
        return maxDatumRodjenjaVozaca;
    }

    public Date getMinDatumPocetakVoznje() {
        return minDatumPocetakVoznje;
    }

    public Date getDanas() {
        return danas;
    }

    public DualListModel<Stanica> getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(DualListModel<Stanica> medjustanice) {
        this.medjustanice = medjustanice;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public void promenaDatuma() {
        Calendar c = Calendar.getInstance();
        c.setTime(noviVozac.getDatumRodjenja());
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 24);
        minDatumPocetakVoznje = c.getTime();
    }

    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tab_0":
                activeTab = "0";
                break;
            case "tab_1":
                activeTab = "1";
                break;
            case "tab_2":
                activeTab = "2";
                break;
            default:
                activeTab = "0";
                break;
        }
    }

    public void otkaziLiniju() {
        Calendar c = Calendar.getInstance();

        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date today = c.getTime();
        if (today.after(this.periodOtkazivanja)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datum do kog je otkazana linija ne sme biti u proslosti.", null));
            return;
        }

        GradskaLinija gl = linije.stream().filter(l -> l.getBrojLinije().equals(this.brojOtkazaneLinije)).findFirst().get();
        if (gl == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fatalna greska: linija nije pronadjena.", null));
            return;
        }
        neotkazaneLinije.remove(gl);
        gl.setAktivna(Boolean.FALSE);
        gl.setOtkazanaDo(this.periodOtkazivanja);

        BeanManager.updateBean(gl);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Linija " + this.brojOtkazaneLinije + " je uspesno otkazana.", null));
    }

    public void dodajVozaca() {
        if (noviVozac == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fatalna greska pri dodavanju vozaca.", null));
            return;
        }

        noviVozac.setTip(0);
        Integer i = BeanManager.addBean(noviVozac);

        if (i == null || i == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska pri dodavanju vozaca.", null));
            return;
        }

        this.vozaci.add(noviVozac);
        noviVozac = new Vozac();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vozac uspesno dodat.", null));

    }

    public Integer dodajLiniju() {
        List<Stanica> dodateStanice = medjustanice.getTarget();
        if (dodateStanice.isEmpty() || dodateStanice.size() < 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Neuspesno dodavanje vozaca."));
            return null;
        }
        this.novaLinija.setPolaznaStanica(dodateStanice.get(0));
        this.novaLinija.setOdredisnaStanica(dodateStanice.get(dodateStanice.size() - 1));

        dodateStanice.remove(0);
        dodateStanice.remove(dodateStanice.size() - 1);
        this.novaLinija.setMedjustanice(dodateStanice);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(this.novaLinija);

            if (id == null) {
                return null;
            }
            
            for (Polazak p : this.novaLinija.getRedVoznje()) {
                p.setGradskaLinija(this.novaLinija);
                id = (Integer) session.save(p);
                if (id == null) {
                    return null;
                }
            }
            
            List<Vozac> dodatiVozaci = this.novaLinija.getVozaci();

            for (Vozac v : dodatiVozaci) {
                v.setGradskaLinija(this.novaLinija);
                this.vozaci.remove(v);
                session.update(v);
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                return null;
            }
        } finally {
            session.close();
        }

        this.linije.add(this.novaLinija);
        this.novaLinija = new GradskaLinija();

        List<Stanica> l = BeanManager.getList("from stanice where tip_stanice = 0");
        this.medjustanice = new DualListModel<>(l, new ArrayList<>());

        return id;
    }

    public void dodajPolazak(AjaxBehaviorEvent e) {
        //TODO: Promeni da se vozaci dodaju kao i stanice da ne bi imao problema sa pozivom
        //      convertera svaki put kada dodas polazak
        Polazak p = new Polazak();
        p.setVremePolaska(vremePolaska);
        novaLinija.getRedVoznje().add(p);
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Stanica) item).getNaziv()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public void onDateSelect(SelectEvent event) {
        //TODO: Vidi zasto se na frontu vreme renderuje kao sati-1
        FacesContext context = FacesContext.getCurrentInstance();
        SimpleDateFormat std = new SimpleDateFormat("HH:mm");

        this.vremePolaska = (Date) event.getObject();
    }
    

}
