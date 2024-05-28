package domain;

import javax.swing.JOptionPane;

public class DoubleTurnCell extends Cell{

	/**
	 * Constructor for the double turn cell
	 * @param fila
	 * @param columna
	 */
	public DoubleTurnCell(int fila, int columna) {
		super(fila,columna);
	}
	

	/**
	 * act for the double turn cell
	 */
	public void act() throws QuoriPOOBException{

		QuoriPOOB.getInstance().cambiaTurno();
		JOptionPane.showMessageDialog(null,"Cayo en una casilla de doble turno");
	}
}
