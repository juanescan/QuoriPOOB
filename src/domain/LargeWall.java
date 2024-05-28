package domain;

import javax.swing.JOptionPane;

public class LargeWall extends Wall {
	
	/**
	 * Constructor for the large wall
	 * @param horizontal
	 * @param xPos
	 * @param yPos
	 * @param player
	 */
	public LargeWall(boolean horizontal,int xPos, int yPos, Player player) {
		super(horizontal, xPos, yPos, player);
		player.minusLargas();
	}
	
	
	/**
	 * Act for the large wall
	 */
	public void act() {
			
		};
	
	/**
	 * put for the large wall
	 */
	public void put() throws QuoriPOOBException {
		int size = QuoriPOOB.getInstance().getSize();
		if(horizontal) {
    		if(yPos <= size -5) {
    			paredHorizontal1(xPos, yPos);
    		}else if(yPos > size -5) {
    			paredHorizontal2(xPos, yPos);
    		}
    	}else if(!horizontal) {
    		if(xPos <= size -5) {
    			paredVertical1(xPos,yPos);
    		}else if( xPos > size - 5) {
    			paredVertical2(xPos,yPos);
    		}
    	}
	}
	
	public boolean canPass() {
		return false;
	}
	

	/**
	 * Put horizontal wall in the board first case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredHorizontal1(int xPos, int yPos) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos,xPos,yPos,yPos+2,yPos+4)) {
			tablero[xPos][yPos] = 9;
			tablero[xPos][yPos+1] = 9;
			tablero[xPos][yPos+2] = 9;
			tablero[xPos][yPos+3] = 9;
			tablero[xPos][yPos+4] = 9;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
		}
	}
	
	/**
	 * put horizontal wall in the board second case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredHorizontal2(int xPos, int yPos) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos,xPos,yPos,yPos-2,yPos-4)) {
			tablero[xPos][yPos] = 9;
			tablero[xPos][yPos-1] = 9;
			tablero[xPos][yPos-2] = 9;
			tablero[xPos][yPos-3] = 9;
			tablero[xPos][yPos-4] = 9;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
		}
	}
	
	/**
	 * put the vertical wall in the board first case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredVertical1(int xPos, int yPos) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos+2,xPos+4,yPos,yPos,yPos)) {
			tablero[xPos][yPos] = 9;
			tablero[xPos+1][yPos] = 9;
			tablero[xPos+2][yPos] = 9;
			tablero[xPos+3][yPos] = 9;
			tablero[xPos+4][yPos] = 9;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
		}
	}
	
	/**
	 * put the vertical wall in the board second case
	 * @param xPos
	 * @param yPos
	 * @throws QuoriPOOBException
	 */
	private void paredVertical2(int xPos, int yPos) throws QuoriPOOBException { 
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(!hayPared(xPos,xPos-2,xPos-4,yPos,yPos,yPos)){
			tablero[xPos][yPos] = 9;
			tablero[xPos-1][yPos] = 9;
			tablero[xPos-2][yPos] = 9;
			tablero[xPos-3][yPos] = 9;
			tablero[xPos-4][yPos] = 9;
			QuoriPOOB.getInstance().getCurrentPlayer().minusNWalls();
			   
		}
	}
	
	/**
	 * check if there is a wall on the site where the new wall is to be put up
	 * @param xPos1
	 * @param xPos2
	 * @param xPos3
	 * @param yPos1
	 * @param yPos2
	 * @param yPos3
	 * @return boolean
	 * @throws QuoriPOOBException
	 */
	private boolean hayPared(int xPos1, int xPos2, int xPos3, int yPos1, int yPos2, int yPos3) throws QuoriPOOBException {
		int[][] tablero = QuoriPOOB.getInstance().getTablero();
		if(tablero[xPos1][yPos1] == 3 || tablero[xPos2][yPos2] == 3 || tablero[xPos3][xPos3] == 3|| tablero[xPos1][yPos1] == 9 
				|| tablero[xPos2][yPos2] == 9 || tablero[xPos3][yPos3] == 9 || tablero[xPos1][yPos1] == 8 || 
				tablero[xPos2][yPos2] == 8 || tablero[xPos3][yPos3] == 8 || tablero[xPos1][yPos1] == 10 ||  
				tablero[xPos2][yPos2] == 10 || tablero[xPos3][yPos3] == 10) {
			JOptionPane.showMessageDialog(null, "No se puede colocar la pared debido a que ya hay otra pared");
			return true;
		}
		return false;
	}
	
	
}
