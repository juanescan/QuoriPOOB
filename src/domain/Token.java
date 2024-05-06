package domain;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * Represents the logic of a token in the Square game.
 * version 1.0
 * Santiago Córdoba
 */
public class Token {
 
    private int fila;
    private int columna;
    private QuoriPOOB game;
    private Color color;

    /**
     * Constructs a new Token with the specified color, row, column, and game reference.
     * 
     * @param color   The color of the token.
     * @param fila    The row index of the token.
     * @param columna The column index of the token.
     * @param game    The Square game instance.
     */
    public Token( int fila, int columna,Color color, QuoriPOOB game) {
        this.fila = fila;
        this.columna = columna;
        this.color = color;
        this.game = game;
    }

    /**
     * Moves the token 
     */
    public void move(int xPos, int yPos) {
        int distanciaFila = Math.abs(fila - xPos);
        int distanciaColumna = Math.abs(columna - yPos);
        int[][] tablero = game.getTablero();
        if (distanciaFila + distanciaColumna > 2||distanciaFila > 2 || distanciaColumna > 2) {
            JOptionPane.showMessageDialog(null, "La casilla seleccionada no es válida para moverse.", "Casilla no válida", JOptionPane.ERROR_MESSAGE);
        }else{
            game.setElemento(fila, columna, 0);
            fila = xPos;
            columna = yPos;
            game.setElemento(fila, columna, 1); 
            game.verificarVictoria();
        	game.cambiaTurno();
        }
        
    }

    /**
     * Gets the row index of the token.
     * 
     * @return The row index of the token.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Gets the column index of the token.
     * 
     * @return The column index of the token.
     */
    public int getColumna() {
        return columna;
    }
    
    public Color getColor() {
    	return color;
    }



}

