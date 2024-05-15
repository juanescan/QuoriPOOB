package domain;

import java.io.Serializable;

public abstract class Cell implements Serializable{
	protected int fila;
	protected int columna;
	
	
	
	public Cell(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public abstract void act();
	
	
	
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
