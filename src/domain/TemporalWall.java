package domain;

import javax.swing.JOptionPane;

public class TemporalWall extends Wall {
	
	private int count = 4;
	
	/**
	 * Constructor for the temporal wall
	 * @param horizontal
	 * @param xPos
	 * @param yPos
	 * @param player
	 */
	public TemporalWall(boolean horizontal,int xPos, int yPos, Player player) {
		super(horizontal, xPos, yPos, player);
		player.minusTemporales();
	}
	

	
	/**
	 *Act for the temporal wall
	 */
	public void act() throws QuoriPOOBException {
		int size = QuoriPOOB.getInstance().getSize();
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		count --;
		if(count == 0) {
			if(horizontal) {
				if(yPos <= size -3) {
					tablero[xPos][yPos] = 2;
					tablero[xPos][yPos+1] = 4;
					tablero[xPos][yPos+2] = 2;
				}else if(yPos > size -3) {
					tablero[xPos][yPos] = 2;
					tablero[xPos][yPos-1] = 4;
					tablero[xPos][yPos-2] = 2;
				}
			}else if(!horizontal) {
				if(xPos <= size -3){
					tablero[xPos][yPos] = 2;
					tablero[xPos+1][yPos] = 4;
					tablero[xPos+2][yPos] = 2;
				}else if(xPos > size - 3) {
					tablero[xPos][yPos] = 2;
					tablero[xPos-1][yPos] = 4;
					tablero[xPos-2][yPos] = 2;
				}
				
			}
			
		}
	}
	
	/**
	 * put for the temporal wall in the game
	 */
	public void put() throws QuoriPOOBException {
		int size = QuoriPOOB.getInstance().getSize();
		if(horizontal) {
    		if(yPos <= size -3) {
    			paredHorizontal1(xPos, yPos);
    		}else if(yPos > size -3) {
    			paredHorizontal2(xPos, yPos);
    		}
    	}else if(!horizontal) {
    		if(xPos <= size -3) {
    			paredVertical1(xPos,yPos);
    		}else if( xPos > size - 3) {
    			paredVertical2(xPos,yPos);
    		}
    	}
	}
	
	public boolean canPass() {
		return false;
	}
	
	/**
	 * put horizontal wall in the board in the first case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredHorizontal1(int xPos, int yPos) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos,yPos,yPos+2)) {
			tablero[xPos][yPos] = 8;
			tablero[xPos][yPos+1] = 8;
			tablero[xPos][yPos+2] = 8;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
			act();
		}
	}
	
	/**
	 * put the horizontal wall in the board second case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredHorizontal2(int xPos, int yPos) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos,yPos,yPos-2)) {
			tablero[xPos][yPos] = 8;
			tablero[xPos][yPos-1] = 8;
			tablero[xPos][yPos-2] = 8;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
			act();
		}
	}
	
	/**
	 * put vertical wall in the board first case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredVertical1(int xPos, int yPos) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos+2,yPos,yPos)) {
			tablero[xPos][yPos] = 8;
			tablero[xPos+1][yPos] = 8;
			tablero[xPos+2][yPos] = 8;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
			act();
		}
	}
	
	/**
	 * put vertical wall in the board second case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredVertical2(int xPos, int yPos) throws QuoriPOOBException { 
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos-2,yPos,yPos)){
			tablero[xPos][yPos] = 8;
			tablero[xPos-1][yPos] = 8;
			tablero[xPos-2][yPos] = 8;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
			act();
		}
	}
	
	/**
	 * check if there is a wall on the site where the new wall is to be put up
	 * @param xPos1
	 * @param xPos2
	 * @param yPos1
	 * @param yPos2
	 * @return boolean
	 * @throws QuoriPOOBException
	 */
	private boolean hayPared(int xPos1, int xPos2, int yPos1, int yPos2) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(tablero[xPos1][yPos1] == 3 || tablero[xPos2][yPos2] == 3 || tablero[xPos1][yPos1] == 9 || 
				tablero[xPos2][yPos2] == 9 || tablero[xPos1][yPos1] == 8 || 
				tablero[xPos2][yPos2] == 8 || tablero[xPos1][yPos1] == 10 || 
						tablero[xPos2][yPos2] == 10) {
			JOptionPane.showMessageDialog(null, "No se puede colocar la pared debido a que ya hay otra pared");
			return true;
		}
		return false;
	}
}
