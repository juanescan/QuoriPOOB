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
        	QuoriPOOB.resetInstance();
            QuoriPOOB game1 = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "2", "2", "2",false);
            assertNotNull(game1);
            assertEquals(9, game1.getSize()); 
            
            QuoriPOOB.resetInstance();

            QuoriPOOB game2 = QuoriPOOB.getInstance(Color.GREEN, Color.YELLOW, "7", "1", "1", "1", "3", "3", "3",false);
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
        	QuoriPOOB.resetInstance();
            QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "2", "2", "2",false);



            // Verifica que las barreras se han asignado correctamente
            assertEquals(5, game.getPlayer1().getNWalls());
            assertEquals(5, game.getPlayer2().getNWalls());

        } catch (QuoriPOOBException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
    
    @Test
    public void shouldMoveOrthogonallyAPawn() {
        try {
        	QuoriPOOB.resetInstance();
            QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "0", "0", "0",false);
            
            Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();

            // Movimiento hacia abajo
            boolean moved = game.move(initialRowT1 + 2, initialColT1);
            int newRowT1 = t1.getFila();
            int newColT1 = t1.getColumna();
            assertTrue("Pawn should move down", moved);
            assertEquals(initialRowT1 + 2, newRowT1);
            assertEquals(initialColT1, newColT1);

            // Reiniciar juego para el siguiente movimiento
            game.reset();
            t1 = game.getT1();
            initialRowT1 = t1.getFila();
            initialColT1 = t1.getColumna();

            // Movimiento hacia la izquierda
            moved = game.move(initialRowT1, initialColT1 - 2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            assertTrue("Pawn should move left", moved);
            assertEquals(initialRowT1, newRowT1);
            assertEquals(initialColT1 - 2, newColT1);

            // Reiniciar juego para el siguiente movimiento
            game.reset();
            t1 = game.getT1();
            initialRowT1 = t1.getFila();
            initialColT1 = t1.getColumna();

            // Movimiento hacia la derecha
            moved = game.move(initialRowT1, initialColT1 + 2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            assertTrue("Pawn should move right", moved);
            assertEquals(initialRowT1, newRowT1);
            assertEquals(initialColT1 + 2, newColT1);
            t2 = game.getT2();
            initialRowT2 = t2.getFila();
            initialColT2 = t2.getColumna();
            game.cambiaTurno();
            moved = game.move(initialRowT2 - 2, initialColT2);
            int newRowT2 = t2.getFila();
            int newColT2 = t2.getColumna();
            assertTrue("Pawn should move up", moved);
            assertEquals(initialRowT2 - 2, newRowT2);
            assertEquals(initialColT2, newColT2);

        } catch (QuoriPOOBException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }   
   
    

    @Test
    public void shouldMoveDiagonallyAPawn(){
        
    }  
    
    @Test
    public void shouldPlaceANormalBarrier(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "2", "2", "2",false);
    		int row = 3;
            int col = 3;
            boolean isHorizontal = true;
            game.putWall(isHorizontal,row, col, "NormalWall");
            assertTrue( "There should be a barrier at the specified position",game.getTablero()[row][col] == 3);
    	}catch(QuoriPOOBException e) {
    		
    	}
    }     
    
    
    @Test
    public void shouldMoveAPawnOverAPawn(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "0", "0", "0",false);
    		 // Posicionar dos fichas en el tablero
    		Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();

            // Movimiento hacia abajo
            game.move(initialRowT1 + 2, initialColT1);
            int newRowT1 = t1.getFila();
            int newColT1 = t1.getColumna();
            
            game.cambiaTurno();
            
            game.move(initialRowT2 - 2, initialColT2);
            int newRowT2 = t2.getFila();
            int newColT2 = t2.getColumna();
            
            game.cambiaTurno();
            
            game.move(newRowT1 + 2, newColT2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            
            game.cambiaTurno();
            
            boolean moved = game.move(newRowT2 - 4, newColT2);
            newRowT2 = t2.getFila();
            newColT2 = t2.getColumna();
            
            assertTrue(moved);
            
            
           
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    
    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier(){
    	QuoriPOOB.resetInstance();
    	try {
			QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "1", "1", "1", "0", "0", "0",false);
			Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();
			game.putWall(true, 1, 4, "NormalWall");
			game.move(initialRowT2 - 2, initialColT2);
            int newRowT2 = t2.getFila();
            int newColT2 = t2.getColumna();
            game.cambiaTurno();
            boolean moved = game.move(initialRowT1+2,initialColT1);
            assertFalse(moved);
		} catch (QuoriPOOBException e) {
			fail();
		}

    }  
    
    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier(){
    	QuoriPOOB.resetInstance();
    	try {
			QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "1", "1", "1", "0", "0", "0",false);
			Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();
			game.putWall(true, 1, 4, "AllyWall");
			boolean moved = game.move(initialRowT2 +2, initialColT2);
			int newRowT1 = t1.getFila();
            int newColT1 = t1.getColumna();
            
            assertTrue(moved);
		} catch (QuoriPOOBException e) {
			fail();
		}
    } 
    
    
    @Test
    public void shouldKnowWhenSomeoneWonTheGame(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "0", "0", "0",false);
    		 // Posicionar dos fichas en el tablero
    		Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();

            // Movimiento hacia abajo
            game.move(initialRowT1 + 2, initialColT1);
            int newRowT1 = t1.getFila();
            int newColT1 = t1.getColumna();
            
            game.cambiaTurno();
            
            game.move(initialRowT2 - 2, initialColT2);
            int newRowT2 = t2.getFila();
            int newColT2 = t2.getColumna();
            
            game.cambiaTurno();
            
            game.move(newRowT1 + 2, newColT2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            
            game.cambiaTurno();
            
            game.move(newRowT2 - 4, newColT2);
            newRowT2 = t2.getFila();
            newColT2 = t2.getColumna();
            

            game.cambiaTurno();
            
            game.move(newRowT1 + 2, newColT2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            
            game.cambiaTurno();
            
            game.move(newRowT2 - 2, newColT2);
            newRowT2 = t2.getFila();
            newColT2 = t2.getColumna();
               
            boolean win = game.verificarVictoria();
            assertTrue(win);
            
           
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    @Test
    public void shouldKnowTheBarriersLeftForEachPlayer(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "1", "1", "0", "0", "0",false);
    		game.putWall(true, 1, 4, "NormalWall");
    		assertEquals(2,game.getPlayer1().getNormales());
    		game.putWall(true, 3, 4, "LargeWall");
    		assertEquals(0,game.getPlayer1().getLargas());
    		game.putWall(true, 5, 4, "AllyWall");
    		assertEquals(0,game.getPlayer1().getAliadas());
    		
    		assertEquals(2,game.getPlayer1().getNWalls());
           
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    @Test
    public void shouldNotBlockThePassageOfAPlayer(){
        fail();
    } 
    
    @Test
    public void shouldMeetNormalBarrierConditions(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "1", "1", "0", "0", "0",false);
    		game.putWall(true, 1, 4, "NormalWall");
    		assertEquals(2,game.getPlayer1().getNormales());
    		NormalWall w =(NormalWall) game.getWallPlayer1(1, 4);
    		assertEquals(1,w.getxPos());
    		assertEquals(4,w.getyPos());
    		assertEquals(true,w.getHorizontal());
    			
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    @Test
    public void shouldMeetTemporalBarrierConditions(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "1", "1", "1", "0", "0", "0",false);
    		game.putWall(true, 3, 4, "TemporalWall");
    		assertEquals(0,game.getPlayer1().getTemporales());
    		TemporalWall w =(TemporalWall) game.getWallPlayer1(3, 4);
    		assertEquals(3,w.getxPos());
    		assertEquals(4,w.getyPos());
    		assertEquals(true,w.getHorizontal());
    			
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    @Test
    public void shouldMeetLongBarrierConditions(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "1", "0", "0", "0",false);
    		game.putWall(true, 5, 4, "LargeWall");
    		assertEquals(1,game.getPlayer1().getLargas());
    		LargeWall w =(LargeWall) game.getWallPlayer1(5, 4);
    		assertEquals(5,w.getxPos());
    		assertEquals(4,w.getyPos());
    		assertEquals(true,w.getHorizontal());
    			
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    @Test
    public void shouldMeetAlliedBarrierConditions(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "1", "0", "0", "0",false);
    		game.putWall(true, 5, 4, "AllyWall");
    		assertEquals(0,game.getPlayer1().getAliadas());
    		AllyWall w =(AllyWall) game.getWallPlayer1(5, 4);
    		assertEquals(5,w.getxPos());
    		assertEquals(4,w.getyPos());
    		assertEquals(true,w.getHorizontal());
    			
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    } 
    
    
    @Test
    public void shouldNotCreateABoardIfItsNotPossible() {
        assertThrows(QuoriPOOBException.class, () -> {
            QuoriPOOB.resetInstance();
            QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "1", "0", "2", "1", "0", "0", "0", false);
        });
    }   
  
 
    
    @Test
    public void shouldNotMoveOrthogonallyAPawnIfItsNotPossible(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "0", "0", "0",false);
    		Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();
            game.move(initialRowT1 + 2, initialColT1);
            int newRowT1 = t1.getFila();
            int newColT1 = t1.getColumna();
            game.cambiaTurno();
            game.move(initialRowT2 - 2, initialColT2);
            int newRowT2 = t2.getFila();
            int newColT2 = t2.getColumna();
            game.cambiaTurno();
            game.move(newRowT1 + 2, newColT2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            game.cambiaTurno();
            boolean moved = game.move(newRowT2 - 2, newColT2);
            assertFalse(moved);
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    }     
    

    @Test
    public void shouldNotMoveDiagonallyAPawnIfItsNotPossible(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "0", "0", "0",false);
    		 // Posicionar dos fichas en el tablero
    		Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();
            boolean moved =game.move(initialRowT1 + 2, initialColT1+2);
            assertFalse(moved);
            game.cambiaTurno(); 
            boolean moved2 = game.move(initialRowT2 - 2, initialColT2-2);
            assertFalse(moved2);
            game.cambiaTurno();
            boolean moved3 =game.move(initialRowT1 + 2, initialColT1-2);
            assertFalse(moved3);
            game.cambiaTurno();
            boolean moved4 = game.move(initialRowT2 - 2, initialColT2+2);
            assertFalse(moved4);	
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    }  
    
    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "1", "2", "2", "0", "0", "0",false);
    		game.putWall(true, 1, 4, "NormalWall");
    		game.cambiaTurno();
    		game.putWall(true,1, 2, "NormalWall");
    		assertNull(game.getWallPlayer1(1, 2));
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
    }     
    
    
    @Test
    public void shouldNotMoveAPawnOverAPawnIfItsNotPossible(){
    	try {
    		QuoriPOOB.resetInstance();
    		QuoriPOOB game = QuoriPOOB.getInstance(Color.RED, Color.BLUE, "5", "0", "2", "2", "0", "0", "0",false);
    		Token t1 = game.getT1();
            Token t2 = game.getT2();
            int initialRowT1 = t1.getFila();
            int initialColT1 = t1.getColumna();
            int initialRowT2 = t2.getFila();
            int initialColT2 = t2.getColumna();
            game.move(initialRowT1 + 2, initialColT1);
            int newRowT1 = t1.getFila();
            int newColT1 = t1.getColumna();
            game.cambiaTurno();
            game.move(initialRowT2 - 2, initialColT2);
            int newRowT2 = t2.getFila();
            int newColT2 = t2.getColumna();  
            game.cambiaTurno();
            game.move(newRowT1 + 2, newColT2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            game.cambiaTurno();
            game.move(newRowT2 - 4, newColT2);
            newRowT2 = t2.getFila();
            newColT2 = t2.getColumna();  
            game.cambiaTurno();
            game.move(newRowT1 -4, newColT2);
            newRowT1 = t1.getFila();
            newColT1 = t1.getColumna();
            game.cambiaTurno();
            boolean moved = game.move(newRowT2 - 2, newColT2 + 2);
            newRowT2 = t2.getFila();
            newColT2 = t2.getColumna(); 
            assertTrue(moved);
            	
    	}catch(QuoriPOOBException e) {
    		fail();
    	}
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
