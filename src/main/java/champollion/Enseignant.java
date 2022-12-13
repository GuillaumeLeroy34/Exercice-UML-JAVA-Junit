package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    ArrayList<ServicePrevu> service = new ArrayList<>();
    ArrayList<UE> ueEnseignees = new ArrayList<>();
    // TODO : rajouter les autres méthodes présentes dans le diagramme UML

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() { //
        int retour = 0;
        for (ServicePrevu e : service) {
            retour += e.equivalentTD();
        }
        return retour;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE
     * spécifiée en "heures équivalent TD" Pour le calcul : 1 heure de cours
     * magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent
     * TD" 1 heure de TP vaut 0,75h "équivalent TD"
     *
     * @param ueParam l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ueParam) {
        ServicePrevu retour = null;
        for (ServicePrevu s : service) {
            if (s.getMyUE().equals(ueParam)) {
                retour = s;
            }
        }
        if (retour == null) {
            return 0;
        } else {
            return retour.equivalentTD();
        }
    }

    /**
     * retourne une service prévu avec une UE comme paramètre
     *
     * @param ueCherchee l'UE du service recherché
     *
     * @return ServicePrevu recherché;
     */
    public ServicePrevu trouverParUE(UE ueCherchee) {
        ServicePrevu retour = null;
        for (ServicePrevu s : service) {
            if (s.getMyUE().equals(ueCherchee)) {
                retour = s;
            }
        }
        return retour;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        if (this.ueEnseignees == null) { // ce bloc sert dans le cas ou la liste d'UE enseignées est null

            this.ueEnseignees.add(ue);
            this.service.add(new ServicePrevu(ue));
            this.trouverParUE(ue).ajouterEnseignement(volumeCM, volumeTP, volumeTD);
        } else {

            if (this.ueEnseignees.contains(ue)) { // l'enseignant est déjà affecté a l'UE spécifiée
                this.trouverParUE(ue).ajouterEnseignement(volumeCM, volumeTP, volumeTD);
            } else {
                this.ueEnseignees.add(ue);
                this.service.add(new ServicePrevu(ue));
                this.trouverParUE(ue).ajouterEnseignement(volumeCM, volumeTP, volumeTD);
            }
        }
    }
 /** détermine si un enseignant est en sous service
  *     
  * @return booléen vrai si l'enseignant est en sous service, faux autrement 
  */
    public boolean enSousService() { // 
        if (this.heuresPrevues() < 192) {
            return true;
        } else {
            return false;
        }
    }

    // idée de test: reste a planifier doit retourner 0 si en sous service est faux
    /** 
     * 
     * @param ue 
     * @param type le type d'heures ( cm td tp )
     * @return int représentant le total d'heures manquantes
     */
    public int resteAPlanifier(UE ue, TypeIntervention type) {
        int manque = 0;
        int retour = 0;
        if (this.enSousService()) {
            manque = 192 - this.heuresPrevues();
        }

        switch (type) {
            case TP:
                retour = (int) (Math.ceil(manque / 0.75));
                break;
            case CM:
                retour = (int) Math.ceil(manque / 1.5);
                break;
            case TD:
                retour = manque;
                break;
        }
        return retour;
    }
}










