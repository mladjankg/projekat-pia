/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package utils;
import java.util.List;

/**
 *
 * @author Mlađan
 */
public class ApplicationUtils {
    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s);
    }
    
    public static boolean isNullOrEmpty(List l) {
        return l == null || l.isEmpty();
    }
}
