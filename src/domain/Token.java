package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Represents the logic of a token in the game.
 * version 1.0
 * Santiago Córdoba y Juan Cancelado
 */
public class Token implements Serializable {
 
    private int fila;
    private int columna;
    private Color color;
    private List<int[]> casillasValidas = new ArrayList<>();
    private List<int[]> posicionesFicha = new ArrayList<>();


    /**
     * Constructs a new Token with the specified color, row, column, and QuoriPOOB.getInstance() reference.
     * 
     * @param color   The color of the token.
     * @param fila    The row index of the token.
     * @param columna The column index of the token.
     * @param QuoriPOOB.getInstance()    The Square QuoriPOOB.getInstance() instance.
     */
    public Token( int fila, int columna,Color color) {
    	
    	posicionesFicha.add(new int[]{fila, columna});
        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }

    
    /**
     * move the token in the board if is possible
     * @param xPos
     * @param yPos
     * @return
     */
    public boolean move(int xPos, int yPos) {
        casillaPosible(xPos, yPos);
        casillasValidas();
        fichaEnCasillasValidas();
        boolean casillaValida = casillaValida(xPos, yPos);
        if (!casillaValida) {
            JOptionPane.showMessageDialog(null, "La casilla seleccionada no es válida para moverse.", "Casilla no válida", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(pared(xPos,yPos)){
        	JOptionPane.showMessageDialog(null, "No se puede mover debido a que hay una pared");
        }else if (!pared(xPos,yPos)) {
            QuoriPOOB.getInstance().setElemento(fila, columna, 0);
            fila = xPos;
            columna = yPos;
            QuoriPOOB.getInstance().setElemento(fila, columna, 1);
            posicionesFicha.add(new int[]{xPos, yPos});
            return true;
        }
        return false;
    }
    

	/**
	 * verify that the position to which you want to move the token is valid
	 * @param xPos
	 * @param yPos
	 * @return boolean
	 */
	private boolean casillaValida(int xPos, int yPos) {
    	boolean validate = false;
    	for (int[] coordenadas : casillasValidas) {
            if (coordenadas[0] == xPos && coordenadas[1] == yPos) {
                validate = true;
            }
        }
    	return validate;
    }
    

	/**
	 * verify if the move is orthogonally
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	private boolean casillaPosible(int xPos, int yPos) {
    	int distanciaFila = Math.abs(fila - xPos);
        int distanciaColumna = Math.abs(columna - yPos);
    	if(distanciaFila + distanciaColumna > 2||distanciaFila > 2 || distanciaColumna > 2) {
            return false;
    	}    
    	return true;
    }
    
    /**
     * Generate a list of the possibles cells
     */
    private void casillasValidas() {
    	casillasValidas.clear();
    	for(int i = 0; i < QuoriPOOB.getInstance().getSize(); i++) {
    		for(int j = 0; j < QuoriPOOB.getInstance().getSize(); j++) {
    			if(casillaPosible(i,j)) {
    				int[] coordenadas = {i,j};
    				casillasValidas.add(coordenadas);
    			}
    		}
    	}    		
    }
    
    /**
     * check the cases when the token can jump over the other token
     */
    private void fichaEnCasillasValidas() {
    	saltarFichaVerticalArribaAAbajo();
    	saltarFichaVerticalAbajoAArriba();
    	saltarFichasHorizontalIzquierdaADerecha();
    	saltarFichasHorizontalDerechaAIzquierda();
    }
    
    
    /**
     * check when the token can jump over the other token in down direction
     */
    private void saltarFichaVerticalArribaAAbajo() {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        List<int[]> coordenadasToAdd = new ArrayList<>();
        Iterator<int[]> iterator = casillasValidas.iterator();
        while (iterator.hasNext()) {
            int[] coordenadas = iterator.next();
            int filaC = coordenadas[0];
            int columnaC = coordenadas[1];
            if (tablero[filaC][columnaC] == 1) {
                if (filaC > fila && filaC != QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{filaC + 2, columnaC});
                    iterator.remove();
                } else if (filaC > fila && filaC == QuoriPOOB.getInstance().getSize() -1  && columna != 0 && columna != QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila + 2, columna - 2});
                    coordenadasToAdd.add(new int[]{fila + 2, columna + 2});
                    iterator.remove();
                } else if (filaC > fila && filaC == QuoriPOOB.getInstance().getSize() -1  && columna == 0) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna + 2});
                } else if (filaC > fila && filaC == QuoriPOOB.getInstance().getSize() -1  && columna == QuoriPOOB.getInstance().getSize() -1 ) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna - 2});
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }
    
    /**
     * check when the token can jump over the other token in up direction
     */
    private void saltarFichaVerticalAbajoAArriba() {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        List<int[]> coordenadasToAdd = new ArrayList<>();
        Iterator<int[]> iterator = casillasValidas.iterator();
        while (iterator.hasNext()) {
            int[] coordenadas = iterator.next();
            int filaC = coordenadas[0];
            int columnaC = coordenadas[1];
            if (tablero[filaC][columnaC] == 1) {
                if (filaC < fila && filaC != 0) {
                    coordenadasToAdd.add(new int[]{filaC - 2, columnaC});
                    iterator.remove();
                } else if (filaC < fila && filaC == 0 && columna != 0 && columna != QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna - 2});
                    coordenadasToAdd.add(new int[]{fila - 2, columna + 2});
                    iterator.remove();
                } else if (filaC < fila && filaC == 0 && columna == 0) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna + 2});
                    iterator.remove();
                } else if (filaC < fila && filaC == 0 && columna == QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna - 2});
                    iterator.remove();
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }
    
    
    /**
     * check when the token can jump over the other token in right direction
     */
    private void saltarFichasHorizontalIzquierdaADerecha() {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        List<int[]> coordenadasToAdd = new ArrayList<>();
        Iterator<int[]> iterator = casillasValidas.iterator();
        while (iterator.hasNext()) {
            int[] coordenadas = iterator.next();
            int filaC = coordenadas[0];
            int columnaC = coordenadas[1];
            if (tablero[filaC][columnaC] == 1) {
                if (columnaC > columna && columnaC != QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{filaC, columnaC + 2});
                    iterator.remove();
                } else if (columnaC > columna && columnaC == QuoriPOOB.getInstance().getSize() -1  && fila != 0 && fila != QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna + 2});
                    coordenadasToAdd.add(new int[]{fila + 2, columna + 2});
                    iterator.remove();
                } else if (columnaC > columna && columnaC == QuoriPOOB.getInstance().getSize() -1  && fila == 0) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna + 2});
                } else if (columnaC > columna && columnaC == QuoriPOOB.getInstance().getSize() -1  && fila == QuoriPOOB.getInstance().getSize() -1 ) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila - 2, columna + 2});
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }

    /**
     * check when the token can jump over the other token in left direction
     */
    private void saltarFichasHorizontalDerechaAIzquierda() {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        List<int[]> coordenadasToAdd = new ArrayList<>();
        Iterator<int[]> iterator = casillasValidas.iterator();
        while (iterator.hasNext()) {
            int[] coordenadas = iterator.next();
            int filaC = coordenadas[0];
            int columnaC = coordenadas[1];
            if (tablero[filaC][columnaC] == 1) {
                if (columnaC < columna && columnaC != 0) {
                    coordenadasToAdd.add(new int[]{filaC, columnaC - 2});
                    iterator.remove();
                } else if (columnaC < columna && columnaC == 0 && fila != 0 && fila != QuoriPOOB.getInstance().getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna - 2});
                    coordenadasToAdd.add(new int[]{fila + 2, columna - 2});
                    iterator.remove();
                } else if (columnaC < columna && columnaC == 0 && fila == 0) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna - 2});
                } else if (columnaC < columna && columnaC == 0 && fila == QuoriPOOB.getInstance().getSize() -1 ) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila - 2, columna - 2});
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }
    
    
    /**
     * check for walls
     * @param xPos
     * @param yPos
     * @return boolean
     */
    private boolean pared(int xPos, int yPos) {
    	if(paredArriba(xPos, yPos) || paredAbajo(xPos, yPos) || paredDerecha(xPos, yPos) || 
    			paredIzquierda(xPos, yPos)) {
    		return true;
    	}
    	return false;
    }
    /**
     * check for up walls
     * @param xPos
     * @param yPos
     * @return boolean
     */
    private boolean paredArriba(int xPos, int yPos) {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        if (xPos < fila) {
            if (tablero[xPos + 1][yPos] == 3 || tablero[xPos + 1][yPos] == 8 || tablero[xPos + 1][yPos] == 9) {
                return true;
            } else if (tablero[xPos + 1][yPos] == 10) {
                QuoriPOOB game = QuoriPOOB.getInstance();
                Player currentPlayer = game.getCurrentPlayer();
                Wall w = null;

                if (currentPlayer == game.getPlayer1()) {
                    w = game.getWallPlayer1(xPos + 1, yPos);
                } else if (currentPlayer == game.getPlayer2()) {
                    w = game.getWallPlayer2(xPos + 1, yPos);
                }

                if (w == null || w.getPlayer() != currentPlayer) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    
    /**
     * check for down walls
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean paredAbajo(int xPos, int yPos) {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        if (xPos > fila) {
            if (tablero[xPos - 1][yPos] == 3 || tablero[xPos - 1][yPos] == 9 || tablero[xPos - 1][yPos] == 8) {
                return true;
            } else if (tablero[xPos - 1][yPos] == 10) {
                QuoriPOOB game = QuoriPOOB.getInstance();
                Player currentPlayer = game.getCurrentPlayer();
                Wall w = null;

                if (currentPlayer == game.getPlayer1()) {
                    w = game.getWallPlayer1(xPos - 1, yPos);
                } else if (currentPlayer == game.getPlayer2()) {
                    w = game.getWallPlayer2(xPos - 1, yPos);
                }

                if (w == null || w.getPlayer() != currentPlayer) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    
    /**
     * check for right walls
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean paredDerecha(int xPos, int yPos) {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        if (yPos > columna) {
            if (tablero[xPos][yPos - 1] == 3 || tablero[xPos][yPos - 1] == 9 || tablero[xPos][yPos - 1] == 8) {
                return true;
            } else if (tablero[xPos][yPos - 1] == 10) {
                QuoriPOOB game = QuoriPOOB.getInstance();
                Player currentPlayer = game.getCurrentPlayer();
                Wall w = null;

                if (currentPlayer == game.getPlayer1()) {
                    w = game.getWallPlayer1(xPos, yPos - 1);
                } else if (currentPlayer == game.getPlayer2()) {
                    w = game.getWallPlayer2(xPos, yPos - 1);
                }

                if (w == null || w.getPlayer() != currentPlayer) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    /**
     * check for left walls
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean paredIzquierda(int xPos, int yPos) {
        int[][] tablero = QuoriPOOB.getInstance().getTablero();
        if (yPos < columna) {
            if (tablero[xPos][yPos + 1] == 3 || tablero[xPos][yPos + 1] == 9 || tablero[xPos][yPos + 1] == 8) {
                return true;
            } else if (tablero[xPos][yPos + 1] == 10) {
                QuoriPOOB game = QuoriPOOB.getInstance();
                Player currentPlayer = game.getCurrentPlayer();
                Wall w = null;

                if (currentPlayer == game.getPlayer1()) {
                    w = game.getWallPlayer1(xPos, yPos + 1);
                } else if (currentPlayer == game.getPlayer2()) {
                    w = game.getWallPlayer2(xPos, yPos + 1);
                }

                if (w == null || w.getPlayer() != currentPlayer) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    	
    
    public List<int[]> getPosicionesFicha() {
		return posicionesFicha;
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
    
    public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
    
    public Color getColor() {
    	return color;
    }

    
    public List<int[]> getCasillasValidas() {
		return casillasValidas;
	}


}

