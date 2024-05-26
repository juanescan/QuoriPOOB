package domain;

import javax.swing.JOptionPane;

public class NormalWall extends Wall {
	
	public NormalWall(boolean horizontal,int xPos, int yPos, Player player) {
		super(horizontal, xPos, yPos, player);
		player.minusNormales();
	}
	
	private QuoriPOOB getInstanceOfGame() throws QuoriPOOBException {
		return QuoriPOOB.getInstance();
	}
	
	public void act() {
		
	};
	
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
			tablero[xPos][yPos] = 3;
			tablero[xPos][yPos+1] = 3;
			tablero[xPos][yPos+2] = 3;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
		}
	}
	
	private void paredHorizontal2(int xPos, int yPos) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos,yPos,yPos-2)) {
			tablero[xPos][yPos] = 3;
			tablero[xPos][yPos-1] = 3;
			tablero[xPos][yPos-2] = 3;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
		}
	}
	
	private void paredVertical1(int xPos, int yPos) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos+2,yPos,yPos)) {
			tablero[xPos][yPos] = 3;
			tablero[xPos+1][yPos] = 3;
			tablero[xPos+2][yPos] = 3;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
		}
	}
	
	private void paredVertical2(int xPos, int yPos) throws QuoriPOOBException { 
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(!hayPared(xPos,xPos-2,yPos,yPos)){
			tablero[xPos][yPos] = 3;
			tablero[xPos-1][yPos] = 3;
			tablero[xPos-2][yPos] = 3;
			game.getCurrentPlayer().minusNWalls();
			game.cambiaTurno();
		}
	}
	
	private boolean hayPared(int xPos1, int xPos2, int yPos1, int yPos2) throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		int[][] tablero = game.getTablero();
		if(tablero[xPos1][yPos1] == 3 || tablero[xPos2][yPos2] == 3 || tablero[xPos1][yPos1] == 9 || 
				tablero[xPos2][yPos2] == 9 || tablero[xPos1][yPos1] == 8 || 
				tablero[xPos2][yPos2] == 8) {
			JOptionPane.showMessageDialog(null, "No se puede colocar la pared debido a que ya hay otra pared");
			return true;
		}
		return false;
	}
}
