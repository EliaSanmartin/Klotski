package klotski_Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Klotski.board;




public class TestBoard {

	@Test
    public void testImpostaFormazione() {
        board b = new board();
        b.imposta_formazione(1);
        //assertEquals(10, b.pezzi.size());
        //assertEquals(10, b.pezzi.size());
    }
    
    
   /* @Test
    public void testPulisciMatrice() {
        board b = new board();
        b.imposta_formazione(1);
        b.mat[0][0] = 1;
        b.mat[1][1] = 50;
        b.pulisci_matrice();
        assertEquals(0, b.mat[0][0]);
        assertEquals(50, b.mat[1][1]);
    }
    
    @Test
    public void testSelezionaMossa() {
        board b = new board();
        b.imposta_formazione(1);
        b.mat[0][0] = 1;
        b.mat[0][1] = 2;
        assertEquals(1, b.seleziona_mossa());
        assertEquals(2, b.seleziona_mossa());
    }*/
}


