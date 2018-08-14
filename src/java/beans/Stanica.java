/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.TipStanice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mlađan
 */
@Entity(name = "stanice")
public class Stanica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    private String naziv;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tip_stanice")
    private TipStanice tipStanice;

    public Stanica() {
    }

    public Stanica(String naziv, TipStanice tipStanice) {
        this.naziv = naziv;
        this.tipStanice = tipStanice;
    }
    
    
}
