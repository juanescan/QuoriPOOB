package domain;

import javax.swing.JOptionPane;

public class TemporalWall extends Wall {
	
	private int count = 4;
	
	public TemporalWall(boolean horizontal,int xPos, int yPos, Player player) {
		super(horizontal, xPos, yPos, player);
		player.minusTemporales();
	}
	
	private QuoriPOOB getInstanceOfGame() throws QuoriPOOBException {
		return QuoriPOOB.getInstance();
	}
	
	public void act() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int size = game.getSize();
		int[][] tablero = game.getTablero();
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
	
	public void put() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int size = game.getSize();
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
	
	private void paredHorizontal1(int xPos, int yPos) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos,yPos,yPos+2)) {
			tablero[xPos][yPos] = 8;
			tablero[xPos][yPos+1] = 8;
			tablero[xPos][yPos+2] = 8;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
			act();
		}
	}
	
	private void paredHorizontal2(int xPos, int yPos) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos,yPos,yPos-2)) {
			tablero[xPos][yPos] = 8;
			tablero[xPos][yPos-1] = 8;
			tablero[xPos][yPos-2] = 8;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
			act();
		}
	}
	
	private void paredVertical1(int xPos, int yPos) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos+2,yPos,yPos)) {
			tablero[xPos][yPos] = 8;
			tablero[xPos+1][yPos] = 8;
			tablero[xPos+2][yPos] = 8;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
			act();
		}
	}
	
	private void paredVertical2(int xPos, int yPos) throws QuoriPOOBException { 
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos-2,yPos,yPos)){
			tablero[xPos][yPos] = 8;
			tablero[xPos-1][yPos] = 8;
			tablero[xPos-2][yPos] = 8;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
			act();
		}
	}
	
	private boolean hayPared(int xPos1, int xPos2, int yPos1, int yPos2) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(tablero[xPos1][yPos1] == 3 || tablero[xPos2][yPos2] == 3 || tablero[xPos1][yPos1] == 9 || 
				tablero[xPos2][yPos2] == 9 || tablero[xPos1][yPos1] == 8 || 
						tablero[xPos2][yPos2] == 8 || tablero[xPos1][yPos1] == 10 || tablero[xPos2][yPos2] == 10 ) {
			JOptionPane.showMessageDialog(null, "No se puede colocar la pared debido a que ya hay otra pared");
			return true;
		}
		return false;
	}
}
