/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package controllers.admin;

import beans.GradskaLinija;
import beans.Stanica;
import beans.Vozac;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Mlađan
 */
public class ManageGradskeLinijeViewControllerTest {
    
    public ManageGradskeLinijeViewControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of otkaziLiniju method, of class ManageGradskeLinijeViewController.
     */
    @Test
    public void testOtkaziLiniju() {
        System.out.println("otkaziLiniju");
        ManageGradskeLinijeViewController instance = new ManageGradskeLinijeViewController();
        instance.otkaziLiniju();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dodajVozaca method, of class ManageGradskeLinijeViewController.
     */
    @Test
    public void testDodajVozaca() {
        System.out.println("dodajVozaca");
        ManageGradskeLinijeViewController instance = new ManageGradskeLinijeViewController();
        instance.dodajVozaca();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dodajLiniju method, of class ManageGradskeLinijeViewController.
     */
    @Test
    public void testDodajLiniju() {
        System.out.println("dodajLiniju");
        ManageGradskeLinijeViewController instance = new ManageGradskeLinijeViewController();
        ArrayList<Stanica> list = new ArrayList<>();

        instance.dodajLiniju();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
