package champollion;

public class ServicePrevu {

    private UE myUE;
    private int heuresCM;
    private int heuresTP;
    private int heuresTD;

    public ServicePrevu(UE myUE) {
        this.myUE = myUE;
        this.heuresCM = 0;
        this.heuresTP = 0;
        this.heuresTD = 0;
    }

    public ServicePrevu(int heuresCM, int heuresTP, int heuresTD, UE ue) {
        this.heuresCM = heuresCM;
        this.heuresTP = heuresTP;
        this.heuresTD = heuresTD;
        this.myUE = ue;
    }

    public void ajouterEnseignement(int heuresCM, int heuresTP, int heuresTD) {
        this.heuresCM += heuresCM;
        this.heuresTP += heuresTP;
        this.heuresTD += heuresTD;
    }

/** renvoie l'équivalent td du service prévu
 * 
 * @return 
 */
    public int equivalentTD() {
        
       int retour = (int) (Math.round(heuresCM * 1.5) + Math.round(heuresTP * 0.75) + heuresTD);
       return retour;
    }

    public UE getMyUE() {
        return myUE;
    }

}
