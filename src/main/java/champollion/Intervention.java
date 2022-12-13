/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.Date;

/**
 *
 * @author gleroy
 */
public class Intervention{
    private Date debut;
    private int duree;  // en heures
    private TypeIntervention type;
    private UE ue;
    private Salle salle;
    private boolean annulee;

    public Intervention(Date debut, int duree, TypeIntervention type, UE ue, Salle salle) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = false;
        
        this.type = type;
        this.ue = ue;
        this.salle = salle;
    }
    
    
    public void annulation(){
        this.annulee = true;
    }
    
    
    
}
