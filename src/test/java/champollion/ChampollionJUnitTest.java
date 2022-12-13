package champollion;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ChampollionJUnitTest {

    Enseignant untel;
    UE uml, java;
     ServicePrevu serviceUML;
    @BeforeEach
    public void setUp() {
        untel = new Enseignant("untel", "untel@gmail.com");
        uml = new UE("UML");
        java = new UE("Programmation en java");
        
    }

    @Test
    public void testNouvelEnseignantSansService() {
        assertEquals(0, untel.heuresPrevues(),
                "Un nouvel enseignant doit avoir 0 heures prévues");
    }

    @Test
    public void testAjouteHeures() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertEquals(10, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

        // 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);

        assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

    }


    @Test

    public void testEstEnSousService() {
        assertTrue(untel.enSousService(),
                "l'enseignant n'est pas noté comme en sous service alors que son nombre d'heures équivalent td est inférieur a 192");
    }

    @Test
    public void testNonEnSousService() {
        untel.ajouteEnseignement(uml, 0, 192, 0);
        assertFalse(untel.enSousService(),
                "l'enseignant est noté comme en sous service alors qu'il a 192h d'équivalent td");
    }

    @Test
    public void resteAPlanifierInitialCorrect() {
        assertEquals(192, untel.resteAPlanifier(uml, TypeIntervention.TD),
                "l'enseignant fraîchement créé n'a pas un reste a planifier de 192 heures TD");

    }

    @Test
    public void resteAPlanifierNonNul() {
        untel.ajouteEnseignement(uml, 0, 20, 0);
        assertEquals(172, untel.resteAPlanifier(uml, TypeIntervention.TD),
                "le nombre d'heures restantes à affecter n'est pas correct");
    }

    @Test
    public void heuresPrevues() {
        untel.ajouteEnseignement(uml, 0, 10, 0);
        untel.ajouteEnseignement(java, 0, 30, 0);
        assertEquals(10 + 30, untel.heuresPrevues(),
                "le nombre d'heure ne prend pas en compte toutes les UE");

    }

}
