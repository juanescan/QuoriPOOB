package domain;

import javax.swing.JOptionPane;

public class TeletransportatorCell extends Cell{
	
	/**
	 * Constructor for the teletransportator cell
	 * @param fila
	 * @param columna
	 */
	public TeletransportatorCell(int fila, int columna) {
		super(fila,columna);
	}
	
	/**
	 * Act for the teletransportator cell
	 */
	public void act() throws QuoriPOOBException{
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
	
	/**
	 * Options for the teletransportator
	 * @param tipo
	 * @throws QuoriPOOBException
	 */
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
	
	/**
	 * move up in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveArriba() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(fila != 0) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        fila = fila -2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	
	/**
	 * move down in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveAbajo() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int size = QuoriPOOB.getInstance().getSize();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(fila != size -1 ) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        fila = fila + 2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	
	/**
	 * move right in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveDer() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = QuoriPOOB.getInstance().getSize();
		if(columna != size -1) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        columna = columna +2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
	}
	
	/**
	 * move left in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveIzq() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(columna != 0) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        columna = columna - 2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}	
	}
	
	/**
	 * move diagonally up right in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveDiaArrDer() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = QuoriPOOB.getInstance().getSize();
		if(fila != 0 && columna != size -1) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        fila = fila -2;
	        columna = columna +2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
	}
	
	/**
	 * move diagonally up left in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveDiaArrIzq() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		if(fila != 0 && columna != 0) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        fila = fila -2;
	        columna = columna -2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	
	/**
	 * move diagonally down right in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveDiaAbaDer() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = QuoriPOOB.getInstance().getSize();
		if(fila != size -1 && columna != size -1 ) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        fila = fila +2;
	        columna = columna +2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}	
	}
	
	/**
	 * move diagonally down left in the teleportation
	 * @throws QuoriPOOBException
	 */
	private void  moveDiaAbaIzq() throws QuoriPOOBException {
		Token t = QuoriPOOB.getInstance().getCurrentPlayer().getToken();
		int fila = t.getFila();
		int columna = t.getColumna();
		int size = QuoriPOOB.getInstance().getSize();
		if(fila != size -1 && columna != 0) {
			QuoriPOOB.getInstance().setElemento(fila, columna, 0);
	        fila = fila +2;
	        columna = columna -2;
	        QuoriPOOB.getInstance().setElemento(fila, columna, 1);
	        t.setFila(fila);
	        t.setColumna(columna);
		}else {
			JOptionPane.showMessageDialog(null, "No se puede mover a esa casilla");
			act();
		}
		
	}
	

}
