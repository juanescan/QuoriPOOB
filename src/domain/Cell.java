package domain;

import java.io.Serializable;

public abstract class Cell implements Serializable{
	protected int fila;
	protected int columna;
	
	
	
	/**
	 * Cosntructor for the cells
	 * @param fila
	 * @param columna
	 */
	public Cell(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * abstract method act for the cells
	 * @throws QuoriPOOBException
	 */
	public abstract void act() throws QuoriPOOBException;
	
	
	
	public int getFila() {
		return fila;
	}



	public void setFila(int fila) {
		this.fila = fila;
	}



	public int getColumna() {
		return columna;
	}



	public void setColumna(int columna) {
		this.columna = columna;
	}
}
