/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package beans.managers;

import beans.Korisnik;
import beans.Poruka;
import javax.persistence.NoResultException;
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
public class BeanManager {

    
    public static Object getBean(Class beanClass, Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;

        Object object = null;
        try {
            tx = session.beginTransaction();
            object = session.get(beanClass, id);            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            session.close();
        }

        return object;
    }
    
    public static Integer addBean(Object o) {
        if (o == null) {
            return null;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        Integer beanId = null;

        try {
            tx = session.beginTransaction();
            beanId = (Integer) session.save(o);            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            session.close();
        }

        return beanId;
    }

    public static void updateBean(Object o) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteBean(Class beanClass, Integer id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Object o = session.get(beanClass, id);
            session.delete(o);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
