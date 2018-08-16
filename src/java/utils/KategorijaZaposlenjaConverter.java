/*
 * Mladjan Mihajlovic 
 * Programiranje internet aplikacija | Elektrotehnicki fakultet | Avgust 2018
 */
package utils;

import enums.KategorijaZaposlenja;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mlađan
 */
@FacesConverter(value="kategorijaZaposlenjaConverter")
public class KategorijaZaposlenjaConverter extends EnumConverter {

    public KategorijaZaposlenjaConverter() {
        super(KategorijaZaposlenja.class);
    }

}

