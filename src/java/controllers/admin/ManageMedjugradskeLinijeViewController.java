/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.admin;

import beans.Autobus;
import beans.MedjugradskaLinija;
import beans.PolazakMedjugradska;
import beans.Prevoznik;
import beans.Stanica;
import beans.Vozac;
import beans.managers.BeanManager;
import controllers.ApplicationController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "medjugradskeView")
@ViewScoped
public class ManageMedjugradskeLinijeViewController implements Serializable {

    class FileContents implements Serializable {

        public FileContents(byte[] content, String fileName) {
            this.content = content;
            this.fileName = fileName;
        }

        public byte[] content;
        public String fileName;
    }

    private String activeTab;

    private DualListModel<Stanica> medjustanice;
    private List<Stanica> stanice;

    private List<Vozac> vozaci;
    private List<MedjugradskaLinija> linije;

    private Vozac noviVozac;
    private Date minDatumPocetakVoznje;

    private Autobus noviAutobus;
    private List<Autobus> autobusi;

    private Prevoznik noviPrevoznik;
    private List<Prevoznik> prevoznici;

    private List<FileContents> slikeAutobusa;

    private FileContents logoPrevoznika;

    private int stepsIndex;
    private MedjugradskaLinija novaLinija;

    private PolazakMedjugradska noviPolazak;
    private int polazakStepsIndex;
    private Prevoznik izabraniPrevoznik;

    public ManageMedjugradskeLinijeViewController() {
        this.autobusi = new ArrayList<>();
        this.prevoznici = new ArrayList<>();

        this.stepsIndex = 0;
        this.activeTab = "0";
        this.noviVozac = new Vozac();
        this.noviPrevoznik = new Prevoznik();
        this.noviAutobus = new Autobus();
        this.slikeAutobusa = new ArrayList<>();

        this.vozaci = new ArrayList<>();
        this.linije = new ArrayList<>();

        this.medjustanice = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
        this.stanice = new ArrayList<>();
        this.novaLinija = new MedjugradskaLinija();
        this.noviPolazak = new PolazakMedjugradska();
    }

    @PostConstruct
    public void init() {
        this.linije = BeanManager.getList("from medjugradske_linije");
        this.prevoznici = BeanManager.getList("from prevoznici");
        this.autobusi = BeanManager.getList("from autobusi");
        this.vozaci = BeanManager.getList("from vozaci where tip=1");
        Date sad = Calendar.getInstance().getTime();

    }

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }

    public List<Vozac> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<Vozac> vozaci) {
        this.vozaci = vozaci;
    }

    public List<MedjugradskaLinija> getLinije() {
        return linije;
    }

    public void setLinije(List<MedjugradskaLinija> linije) {
        this.linije = linije;
    }

    public Vozac getNoviVozac() {
        return noviVozac;
    }

    public void setNoviVozac(Vozac noviVozac) {
        this.noviVozac = noviVozac;
    }

    public Date getMinDatumPocetakVoznje() {
        return minDatumPocetakVoznje;
    }

    public void setMinDatumPocetakVoznje(Date minDatumPocetakVoznje) {
        this.minDatumPocetakVoznje = minDatumPocetakVoznje;
    }

    public Autobus getNoviAutobus() {
        return noviAutobus;
    }

    public void setNoviAutobus(Autobus noviAutobus) {
        this.noviAutobus = noviAutobus;
    }

    public List<Autobus> getAutobusi() {
        return autobusi;
    }

    public void setAutobusi(List<Autobus> autobusi) {
        this.autobusi = autobusi;
    }

    public Prevoznik getNoviPrevoznik() {
        return noviPrevoznik;
    }

    public void setNoviPrevoznik(Prevoznik noviPrevoznik) {
        this.noviPrevoznik = noviPrevoznik;
    }

    public List<Prevoznik> getPrevoznici() {
        return prevoznici;
    }

    public void setPrevoznici(List<Prevoznik> prevoznici) {
        this.prevoznici = prevoznici;
    }

    public int getStepsIndex() {
        return stepsIndex;
    }

    public void setStepsIndex(int stepsIndex) {
        this.stepsIndex = stepsIndex;
    }

    public MedjugradskaLinija getNovaLinija() {
        return novaLinija;
    }

    public void setNovaLinija(MedjugradskaLinija novaLinija) {
        this.novaLinija = novaLinija;
    }

    public DualListModel<Stanica> getMedjustanice() {
        List<Stanica> source = stanice.stream()
                .filter(s
                        -> !s.equals(this.novaLinija.getPolaznaStanica())
                && !s.equals(this.novaLinija.getOdredisnaStanica()))
                .collect(toList());
        medjustanice.setSource(source);
        return medjustanice;
    }

    public void setMedjustanice(DualListModel<Stanica> medjustanice) {
        this.medjustanice = medjustanice;
    }

    public List<Stanica> getStanice() {
        return stanice;
    }

    public List<Stanica> getOdredisneStanice() {
        if (this.novaLinija.getPolaznaStanica() == null) {
            return stanice;
        } else {
            return this.stanice.stream().filter(s -> !s.equals(this.novaLinija.getPolaznaStanica())).collect(toList());
        }
    }

    public void setStanice(List<Stanica> stanice) {
        this.stanice = stanice;
    }

    public PolazakMedjugradska getNoviPolazak() {
        return noviPolazak;
    }

    public void setNoviPolazak(PolazakMedjugradska noviPolazak) {
        this.noviPolazak = noviPolazak;
    }

    public int getPolazakStepsIndex() {
        return polazakStepsIndex;
    }

    public void setPolazakStepsIndex(int polazakStepsIndex) {
        this.polazakStepsIndex = polazakStepsIndex;
    }

    public Prevoznik getIzabraniPrevoznik() {
        return izabraniPrevoznik;
    }

    public void setIzabraniPrevoznik(Prevoznik izabraniPrevoznik) {
        this.izabraniPrevoznik = izabraniPrevoznik;
    }

    public List<MedjugradskaLinija> getLinijePrevoznika() {
        if (this.izabraniPrevoznik == null) {
            return new ArrayList<>();
        }
        return linije.stream().filter(f -> f.getPrevoznik().equals(this.izabraniPrevoznik)).collect(toList());
    }

    public List<Autobus> getDostupniAutobusi() {
        if (this.izabraniPrevoznik == null
                || this.noviPolazak == null
                || this.noviPolazak.getMedjugradskaLinija() == null
                || this.noviPolazak.getVremeDolaska() == null
                || this.noviPolazak.getVremePolaska() == null) {
            return null;
        }

        List<Autobus> l = this.autobusi
                .stream()
                .filter(a
                        -> this.noviPolazak.getMedjugradskaLinija().getPrevoznik().equals(a.getPrevoznik()))
                .collect(toList());

        l = l.stream()
                .filter((a) -> {
                    Autobus au = (Autobus) a;
                    if (au.getPolasci() == null || au.getPolasci().isEmpty()) {
                        return true;
                    } else {
                        for (PolazakMedjugradska p : au.getPolasci()) {
                            if (!((this.noviPolazak.getVremeDolaska().before(p.getVremePolaska())
                                    && (this.noviPolazak.getVremePolaska().before(p.getVremePolaska())))
                                    || ((this.noviPolazak.getVremeDolaska().after(p.getVremeDolaska()))
                                    && (this.noviPolazak.getVremePolaska().after(p.getVremeDolaska()))))) {
                                return false;
                            }
                        }

                        return true;
                    }
                })
                .collect(toList());
        return l;
    }

    public List<Vozac> getDostupniVozaci() {
        if (this.izabraniPrevoznik == null
                || this.noviPolazak == null
                || this.noviPolazak.getMedjugradskaLinija() == null) {
            return null;
        }

        List<Vozac> l = this.vozaci
                .stream()
                .filter(a
                        -> this.noviPolazak.getMedjugradskaLinija().getPrevoznik().equals(a.getPrevoznik()))
                .collect(toList());

        l = l.stream()
                .filter((a) -> {
                    Vozac v = (Vozac) a;
                    if (v.getPolasci() == null || v.getPolasci().isEmpty()) {
                        return true;
                    } else {
                        for (PolazakMedjugradska p : v.getPolasci()) {
                            if (!((this.noviPolazak.getVremeDolaska().before(p.getVremePolaska())
                                    && (this.noviPolazak.getVremePolaska().before(p.getVremePolaska())))
                                    || ((this.noviPolazak.getVremeDolaska().after(p.getVremeDolaska()))
                                    && (this.noviPolazak.getVremePolaska().after(p.getVremeDolaska()))))) {
                                return false;
                            }
                        }

                        return true;
                    }
                })
                .collect(toList());
        return l;
    }

    public void sledeci() {
        ++this.stepsIndex;
    }

    public void prethodni() {
        --this.stepsIndex;
    }

    public void sledeciKorakPolazak() {
        ++this.polazakStepsIndex;
    }

    public void prethodniKorakPolazak() {
        --this.polazakStepsIndex;
    }

    //Metoda za menjanje taba na view-u
    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tab_0":
                this.activeTab = "0";
                break;
            case "tab_1":
                this.activeTab = "1";

                this.stanice = BeanManager.getList("from stanice where tip_stanice=1");
                List<Stanica> dlm = new ArrayList<>();
                dlm.addAll(this.stanice);
                this.medjustanice = new DualListModel<>(dlm, new ArrayList<>());
                break;
            case "tab_2":
                this.activeTab = "2";
                break;
            case "tab_3":
                this.activeTab = "3";
                break;
            case "tab_4":
                this.activeTab = "4";
                break;
            default:
                this.activeTab = "0";
                break;
        }
    }

    public void dodajPolazak() {
        this.noviPolazak.setPreostaloMesta(this.noviPolazak.getAutobus().getBrojMesta());

        Integer id = BeanManager.addBean(this.noviPolazak);
        if (id == null) {
            //TODO: Add message.
            return;
        }

        //this.noviPolazak.getMedjugradskaLinija().getPolasci().add(this.noviPolazak);
        for (MedjugradskaLinija m: this.linije) {
            if (m.getId().equals(this.noviPolazak.getMedjugradskaLinija().getId())) {
                m.getPolasci().add(this.noviPolazak);
            }
        }

        //this.noviPolazak.getVozac().getPolasci().add(this.noviPolazak);
        for(Vozac v: this.vozaci) {
            if (v.getId().equals(this.noviPolazak.getVozac().getId())) {
                v.getPolasci().add(this.noviPolazak);
            }
        }
        
        //this.noviPolazak.getAutobus().getPolasci().add(this.noviPolazak);
        for (Autobus a: this.autobusi) {
            if (a.getId().equals(this.noviPolazak.getAutobus().getId())) {
                a.getPolasci().add(this.noviPolazak);
            }
        }
        
        this.noviPolazak = new PolazakMedjugradska();
        this.izabraniPrevoznik = null;
        this.polazakStepsIndex = 0;
    }

    public void dodajVozaca() {
        if (noviVozac == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Fatalna greska pri dodavanju vozača."));
            return;
        }

        noviVozac.setTip(1);
        Integer i = BeanManager.addBean(noviVozac);

        if (i == null || i == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Greska pri dodavanju vozaca."));
            return;
        }

        this.vozaci.add(noviVozac);
        noviVozac = new Vozac();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Vozač uspešno dodat."));

    }

    /**
     * Metoda koja osluskuje kada se promeni datum u kalendaru za unos datuma
     * rodjenja u formi za dodavanje novog vozaca. Setuje ogranicenja u
     * kalendaru za izbor datuma pocetka voznje vozaca.
     */
    public void promenaDatuma() {
        Calendar c = Calendar.getInstance();
        c.setTime(noviVozac.getDatumRodjenja());
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 24);
        minDatumPocetakVoznje = c.getTime();
    }

    public void promenaDatumaPolazak() {
        if (this.noviPolazak.getVremeDolaska() == null) {
            return;
        }

        if (this.noviPolazak.getVremeDolaska().before(this.noviPolazak.getVremePolaska())) {
            this.noviPolazak.setVremeDolaska(null);
        }
    }

    public void promenaDatumaDolazak() {

    }

    public void dodajAutobus() {

        StringBuilder sb = new StringBuilder();

        String uploadLocation = this.getUploadLocation();

        //Dodeljivanje novih imena slikama i cuvanje slika na serveru.
        for (FileContents file : this.slikeAutobusa) {
            String serverName = this.getServerFileName(file.fileName);
            sb.append(serverName).append(';'); //merging file names into one string
            byte[] content = file.content;
            try (FileOutputStream stream = new FileOutputStream(uploadLocation + serverName)) {
                stream.write(content);
            } catch (IOException e) {
            }

        }

        this.slikeAutobusa = new ArrayList<>();

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); //removing ending ';'
        }

        this.noviAutobus.setSlike(sb.toString());

        Integer id = BeanManager.addBean(this.noviAutobus);
        if (id == null) {
            return;
        }

        this.autobusi.add(this.noviAutobus);
        this.noviAutobus = new Autobus();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Autobus uspešno dodat."));
    }

    public void dodajPrevoznika() {
        //TODO: dodaj regex pri dodavanju broja telefona
        if (logoPrevoznika != null) {
            String uploadLocation = this.getUploadLocation();
            String serverName = this.getServerFileName(logoPrevoznika.fileName);
            byte[] content = logoPrevoznika.content;

            try (FileOutputStream stream = new FileOutputStream(uploadLocation + serverName)) {
                stream.write(content);
            } catch (IOException e) {
                //TODO: Handle exception
            }

            this.noviPrevoznik.setLogoURL(serverName);
        }
        Integer id = BeanManager.addBean(this.noviPrevoznik);

        if (id == null) {
            //TODO: Manage error
            return;
        }

        this.prevoznici.add(this.noviPrevoznik);
        this.noviPrevoznik = new Prevoznik();
        this.logoPrevoznika = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Prevoznik uspešno dodat."));
    }

    public void dodajLiniju() {

        //TODO: Dodaj proveru da li je vec dodata stanica sa istom pocetnom i krajnjom i istim prevoznikom.
        List<MedjugradskaLinija> l = linije.stream().filter(f -> f.getPrevoznik().equals(novaLinija.getPrevoznik())).collect(toList());

        for (MedjugradskaLinija linija : l) {
            if (linija.getPolaznaStanica().equals(novaLinija.getPolaznaStanica())
                    && linija.getOdredisnaStanica().equals(novaLinija.getOdredisnaStanica())) {
                //Ako imaju istu pocetnu i odredisnu stanicu onda proveravaj medjustanice
                //u suprotnom nisu iste
                if (linija.getMedjustanice().size() == novaLinija.getMedjustanice().size()) {
                    //Ukoliko imaju isti broj medjustanica, proveri ih

                    if (linija.getMedjustanice().isEmpty()) {
                        //Ukoliko nemaju medjustanice a imaju istu pocetnu i krajnju stanicu onda su iste
                        FacesContext
                                .getCurrentInstance()
                                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Linija sa unetim parametrima već postoji."));
                        return;

                    } else {
                        //Uporedi svaku medjustanicu
                        boolean equals = true;
                        for (int i = 0; i < linija.getMedjustanice().size(); i++) {
                            if (!linija.getMedjustanice().get(i).equals(linija.getMedjustanice().get(i))) {
                                equals = false;
                                break;
                            }
                        }

                        if (equals) {
                            FacesContext
                                    .getCurrentInstance()
                                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Linija sa unetim parametrima već postoji."));
                            return;
                        }
                    }
                }
            }
        }

        this.novaLinija.setMedjustanice(this.medjustanice.getTarget());
        Integer id = BeanManager.addBean(this.novaLinija);

        if (id == null) {
            return;
        }
        this.linije.add(this.novaLinija);
        this.novaLinija = new MedjugradskaLinija();
        this.medjustanice = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
        this.stepsIndex = 0;
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        byte[] content = file.getContents();

        this.slikeAutobusa.add(new FileContents(content, file.getFileName()));

    }

    public void logoPrevoznikaUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        byte[] content = file.getContents();
        logoPrevoznika = new FileContents(content, file.getFileName());
    }

    private String getServerFileName(String fileName) {
        String name = fileName;
        String extension = name
                .substring(name.lastIndexOf('.') + 1);

        String serverName = UUID.randomUUID().toString();
        serverName += "." + extension;

        return serverName;
    }

    private String getUploadLocation() {
        String uploadLocation = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("upload.location");
        if (!uploadLocation.endsWith(File.separator)) {
            uploadLocation += File.separator;
        }

        return uploadLocation;
    }
}
