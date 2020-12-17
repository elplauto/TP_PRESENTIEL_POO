import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import tpnote.ActionNonAutoriseeException;
import tpnote.Canal;
import tpnote.Habilitation;
import tpnote.Message;
import tpnote.Role;
import tpnote.Utilisateur;

public class testCanal {

	@Test
    public void ecrireMessageOK() throws ActionNonAutoriseeException {
        // Etape 1 : préparation des objets
        Canal c = new Canal();

        Message m = new Message();
        m.setDestinataire(c);
        m.setTexte("Bonne fêtes de fin d'année !");

        Utilisateur utilisateur = new Utilisateur();
        c.getMapping_role_utilisateurs().put(new Role("Membre"), Arrays.asList(utilisateur));
        c.getMapping_role_habilitations().put(new Role("Membre"), Arrays.asList(Habilitation.ECRITURE));

        // Etape 2 : appel de la méthode testée
        c.ecrireMessage(utilisateur, m);

        // Etape 3 test du retour
        List<Message> historiques = c.getHistoriques();
        Assert.assertEquals(1, historiques.size());
        System.out.println(historiques.get(0).getTexte());
    }

    @Test
    public void ecrireMessageKO() {

        // Etape 1 : préparation des objets
        Canal c = new Canal();

        Message m = new Message();
        m.setDestinataire(c);
        m.setTexte("Bonne année 2021 !");

        Utilisateur utilisateur = new Utilisateur();

        // Etape 2 : appel de la méthode testée
        try {
            c.ecrireMessage(utilisateur, m);

            // Etape 3 test du retour
            fail("L'exception aurait du être levée, on ne doit pas passer ici !");
        } catch (ActionNonAutoriseeException e) {

            // Etape 3 test du retour = si on arrive ici, le test est concluant
        }
    }
    
    @Test
    public void testCanalCompareTo() {
    	
    	Canal c1 = new Canal();
    	c1.setOrdre(0);
    	Canal c2 = new Canal();
    	c2.setOrdre(1);
    	
    	//c1 doit etre avant c2 donc < 0
    	Assert.assertTrue(c1.compareTo(c2) < 0);

    }

}


