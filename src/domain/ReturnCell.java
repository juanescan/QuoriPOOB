package domain;

import java.util.List;

import javax.swing.JOptionPane;

public class ReturnCell extends Cell {
	public ReturnCell(int fila, int columna) {
		super(fila,columna);
	}
	
	private QuoriPOOB getInstanceOfGame() throws QuoriPOOBException {
		return QuoriPOOB.getInstance(null, null, null, null, null, null, null, null, null);
	}
	
	public void act() throws QuoriPOOBException{
		QuoriPOOB game = getInstanceOfGame();
		Token t = game.getCurrentPlayer().getToken();
		int xPos = t.getFila();
		int yPos = t.getColumna();
		List<int[]> listaPosiciones = t.getPosicionesFicha();
		int tamaño = listaPosiciones.size();
		if(tamaño < 3) {
			 int[] primeraPosicion = listaPosiciones.get(0);
		     int fila = primeraPosicion[0];
		     int columna = primeraPosicion[1]; 
		     t.move(fila, columna);
		     JOptionPane.showMessageDialog(null,"Cayo en una casilla de regresar");
		}
		else {
			int[] nuevaPosicion = listaPosiciones.get(tamaño-3);
			int fila = nuevaPosicion[0];
			int columna = nuevaPosicion[1];
			game.setElemento(xPos, yPos, 0);
	        t.setFila(fila);
	        t.setColumna(columna);
	        game.setElemento(fila, columna, 1);
			JOptionPane.showMessageDialog(null,"Cayo en una casilla de regresar");
		}
	}
}
