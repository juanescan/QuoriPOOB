package pruebas;



import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import domain.*;

/**
 * The test class Quopripoobv1.
 *
 * @author  POOB
 * @version 2024-1
 */
public class Quopripoobvi
{
    /**
     * Default constructor for test class Qupripoobv1
     */
    public Quopripoobvi()
    {
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes() {
        try {
            QuoriPOOB game1 = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "2", "2", "2", "2", "2", "2");
            assertNotNull(game1);
            assertEquals(9, game1.getSize()); 
            
            QuoriPOOB.resetInstance();

            QuoriPOOB game2 = QuoriPOOB.getInstance(Color.GREEN, Color.YELLOW, "7", "3", "3", "3", "3", "3", "3");
            assertNotNull(game2);
            assertEquals(13, game2.getSize()); 
        } catch (QuoriPOOBException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }    
  
    
    @Test
    public void shouldAssignBarriersToPlayers() {
        try {
            // Crea una instancia del juego
            QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "2", "2", "2", "2", "2", "2");



            // Verifica que las barreras se han asignado correctamente
            assertEquals(2, game.getPlayer1().getNWalls());
            assertEquals(2, game.getPlayer2().getNWalls());

        } catch (QuoriPOOBException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
    
    @Test
    public void shouldMoveOrthogonallyAPawn(){
        fail();
    }     
    

    @Test
    public void shouldMoveDiagonallyAPawn(){
        fail();
    }  
    
    @Test
    public void shouldPlaceANormalBarrier(){
        fail();
    }     
    
    
    @Test
    public void shouldMoveAPawnOverAPawn(){
        fail();
    } 
    
    
    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier(){
        fail();
    }  
    
    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier(){
        fail();
    } 
    
    
    @Test
    public void shouldKnowWhenSomeoneWonTheGame(){
        fail();
    } 
    
    @Test
    public void shouldKnowTheBarriersLeftForEachPlayer(){
        fail();
    } 
    
    @Test
    public void shouldNotBlockThePassageOfAPlayer(){
        fail();
    } 
    
    @Test
    public void shouldMeetNormalBarrierConditions(){
        fail();
    } 
    
    @Test
    public void shouldMeetTemporalBarrierConditions(){
        fail();
    } 
    
    @Test
    public void shouldMeetLongBarrierConditions(){
        fail();
    } 
    
    @Test
    public void shouldMeetAlliedBarrierConditions(){
        fail();
    } 
    
    
    @Test
    public void shouldNotCreateABoardIfItsNotPossible(){
        fail();
    }     
  
 
    
    @Test
    public void shouldNotMoveOrthogonallyAPawnIfItsNotPossible(){
        fail();
    }     
    

    @Test
    public void shouldNotMoveDiagonallyAPawnIfItsNotPossible(){
        fail();
    }  
    
    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible(){
        fail();
    }     
    
    
    @Test
    public void shouldNotMoveAPawnOverAPawnIfItsNotPossible(){
        fail();
    } 
    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    void tearDown() {
        // Reset the singleton instance after each test
        QuoriPOOB.resetInstance();
    }
}
