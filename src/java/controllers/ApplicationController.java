/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import utils.HibernateUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(eager = true, name = "applicationController")
@ApplicationScoped
public class ApplicationController {
   
    
    public ApplicationController() {
    }
    
    @PostConstruct
    public void startup() {
        
        HibernateUtil.getSessionFactory();
    }

    @PreDestroy
    public void shutdown() {
        // ...
    }

    private String visibleName = "BUSOMANIJAK";

    public String getVisibleName() {
        return visibleName;
    }

    
    
    private Calendar getCalendarWithoutTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }
    
    public Date getSutra() {
        Calendar c = this.getCalendarWithoutTime();
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        return c.getTime();
    }

    public Date getMaxDatumRodjenjaVozaca() {
        Calendar c = this.getCalendarWithoutTime();
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 24); //Vozac mora imati makar 24 godine da bi mogao da dobije dozvolu za voznju autobusa
        return c.getTime();
    }

    public Date getDanas() {
        return this.getCalendarWithoutTime().getTime();
    }
    
    public Date getSad() {
        return Calendar.getInstance().getTime();
    }
}

