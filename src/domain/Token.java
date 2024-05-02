package domain;



/**
 * Represents the logic of a token in the Square game.
 * version 1.0
 * Santiago Córdoba
 */
public class Token {
 
    private int fila;
    private int columna;
    private QuoriPOOB game;

    /**
     * Constructs a new Token with the specified color, row, column, and game reference.
     * 
     * @param color   The color of the token.
     * @param fila    The row index of the token.
     * @param columna The column index of the token.
     * @param game    The Square game instance.
     */
    public Token( int fila, int columna, QuoriPOOB game) {
        this.fila = fila;
        this.columna = columna;
        this.game = game;
    }

    /**
     * Moves the token north (up) if possible.
     */
    public void moveNorte() {
        if (fila != 0 ) {
            int newFila = fila - 1;
            game.setElemento(fila, columna, 0);
            game.setElemento(newFila, columna, 1);
            fila = newFila;
        }
    }

    /**
     * Moves the token south (down) if possible.
     */
    public void moveSur() {
        if (fila != game.getSize() - 1 ) {
            int newFila = fila + 1;
            game.setElemento(fila, columna, 0);
            game.setElemento(newFila, columna, 1);
            fila = newFila;
        }
    }

    /**
     * Moves the token east (right) if possible.
     */
    public void moveEste() {
        if (columna != game.getSize() - 1 ) {
            int newColumna = columna + 1;
            game.setElemento(fila, columna, 0);
            game.setElemento(fila, newColumna, 1);
            columna = newColumna;
        }
    }

    /**
     * Moves the token west (left) if possible.
     */
    public void moveOeste() {
        if (columna != 0 ) {
            int newColumna = columna - 1;
            game.setElemento(fila, columna, 0);
            game.setElemento(fila, newColumna, 1);
            columna = newColumna;
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



}

