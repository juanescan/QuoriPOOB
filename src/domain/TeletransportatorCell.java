package domain;

import javax.swing.JOptionPane;

public class TeletransportatorCell extends Cell{
	
	public TeletransportatorCell(int fila, int columna) {
		super(fila,columna);
	}
	
	private QuoriPOOB getInstanceOfGame() throws QuoriPOOBException {
		return QuoriPOOB.getInstance();
	}
	
	public void act() throws QuoriPOOBException{
		QuoriPOOB game = getInstanceOfGame();
		JOptionPane.showMessageDialog(null,"Cayo en una casilla de teletransportar");
		 String[] options = {"↖", "↑", "↗", "←", "→", "↙", "↓", "↘"};
	     String tipo = (String) JOptionPane.showInputDialog(
	     null,
	     "Seleccione el movimiento que desea realizar:",
	     "Movimiento",
	     JOptionPane.PLAIN_MESSAGE,
	     null,
	     options,
	     options[0]);
	     opciones(tipo);
	     
	}
	
	private void opciones(String tipo) throws QuoriPOOBException {
		if (tipo != null) {
	        switch (tipo) {
	            case "↑":
	                moveArriba();
	                break;
	            case "↓":
	                moveAbajo();
	                break;
	            case "→":
	                moveDer();
	                break;
	            case "←":
	                moveIzq();
	                break;
	            case "↗":
	                moveDiaArrDer();
	                break;
	            case "↖":
	                moveDiaArrIzq();
	                break;
	            case "↘":
	                moveDiaAbaDer();
	                break;
	            case "↙":
	                moveDiaAbaIzq();
	                break;
	            default:
	                throw new IllegalArgumentException("Dirección no válida: " + tipo);
	        }
	    }
	}
	
	private void  moveArriba() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(fila != 0) {
			game.setElemento(fila, columna, 0);
	        fila = fila -2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	
	private void  moveAbajo() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int size = game.getSize();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(fila != size -1 ) {
			game.setElemento(fila, columna, 0);
	        fila = fila + 2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	
	private void  moveDer() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = game.getSize();
		if(columna != size -1) {
			game.setElemento(fila, columna, 0);
	        columna = columna +2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
	}
	
	private void  moveIzq() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(columna != 0) {
			game.setElemento(fila, columna, 0);
	        columna = columna - 2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}	
	}
	
	private void  moveDiaArrDer() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = game.getSize();
		if(fila != 0 && columna != size -1) {
			game.setElemento(fila, columna, 0);
	        fila = fila -2;
	        columna = columna +2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
	}
	
	private void  moveDiaArrIzq() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(fila != 0 && columna != 0) {
			game.setElemento(fila, columna, 0);
	        fila = fila -2;
	        columna = columna -2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	
	private void  moveDiaAbaDer() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = game.getSize();
		if(fila != size -1 && columna != size -1 ) {
			game.setElemento(fila, columna, 0);
	        fila = fila +2;
	        columna = columna +2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}	
	}
	
	private void  moveDiaAbaIzq() throws QuoriPOOBException {
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = game.getSize();
		if(fila != size -1 && columna != 0) {
			game.setElemento(fila, columna, 0);
	        fila = fila +2;
	        columna = columna -2;
	        game.setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	

}
