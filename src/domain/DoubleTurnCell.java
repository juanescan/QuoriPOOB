package domain;

import javax.swing.JOptionPane;

public class DoubleTurnCell extends Cell{

	public DoubleTurnCell(int fila, int columna) {
		super(fila,columna);
	}
	
	private QuoriPOOB getInstanceOfGame() throws QuoriPOOBException {
		return QuoriPOOB.getInstance();
	}
	
	public void act() throws QuoriPOOBException{
		QuoriPOOB game = getInstanceOfGame();
		game.cambiaTurno();
		JOptionPane.showMessageDialog(null,"Cayo en una casilla de doble turno");
	}
}
