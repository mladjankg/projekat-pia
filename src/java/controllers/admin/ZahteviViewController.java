/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.admin;

import beans.Karta;
import beans.Poruka;
import beans.managers.BeanManager;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Mlađan
 */
@ManagedBean(name = "zahteviViewController")
@ViewScoped
public class ZahteviViewController implements Serializable {

    @ManagedProperty(value = "#{adminController}")
    private AdminController adminController;

    private String activeTab = "0";

    public void odobriZahtevZaKupovinu(Karta k) {
        k.setAdminPotvrdio(Boolean.TRUE);
        k.setOdobrena(Boolean.TRUE);

        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        if (k.getKorisnik().getKategorijaZaposlenja().isTip()) {
            c.set(c.get(Calendar.YEAR), 12, 31);
        } else {
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        }

        k.setDatumVazenja(c.getTime());

        BeanManager.updateBean(k);
        Poruka p = new Poruka("Vaš zahtev za kupovinu karte je odobren.\n" + k, Calendar.getInstance().getTime(), k.getKorisnik());
        BeanManager.addBean(p);
        adminController.getZahteviZaKupovinu().remove(k);
    }

    public void odbijZahtevZaKupovinu(Karta k) {
        k.setAdminPotvrdio(Boolean.TRUE);
        k.setOdobrena(Boolean.FALSE);
        BeanManager.updateBean(k);

        Poruka p = new Poruka("Vaš zahtev za kupovinu karte je odbijen.\n" + k, Calendar.getInstance().getTime(), k.getKorisnik());
        BeanManager.addBean(p);
        adminController.getZahteviZaKupovinu().remove(k);
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }

    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tab_0":
                activeTab = "0";
                break;
            case "tab_1":
                activeTab = "1";
                break;
            default:
                activeTab = "0";
                break;
        }
    }
}
