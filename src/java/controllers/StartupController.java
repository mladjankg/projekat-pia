/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import utils.HibernateUtil;

/**
 *
 * @author Mlađan
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class StartupController {
    public StartupController() {
    }
    
    @PostConstruct
    public void startup() {
        HibernateUtil.getSessionFactory();
    }

    @PreDestroy
    public void shutdown() {
        // ...
    }
}

