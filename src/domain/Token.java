package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Represents the logic of a token in the Square game.
 * version 1.0
 * Santiago Córdoba y Juan Cancelado
 */
public class Token implements Serializable {
 
    private int fila;
    private int columna;
    private QuoriPOOB game;
    private Color color;
    private List<int[]> casillasValidas = new ArrayList<>();
    private List<int[]> posicionesFicha = new ArrayList<>();


    /**
     * Constructs a new Token with the specified color, row, column, and game reference.
     * 
     * @param color   The color of the token.
     * @param fila    The row index of the token.
     * @param columna The column index of the token.
     * @param game    The Square game instance.
     */
    public Token( int fila, int columna,Color color, QuoriPOOB game) {
    	
    	posicionesFicha.add(new int[]{fila, columna});
        this.fila = fila;
        this.columna = columna;
        this.color = color;
        this.game = game;
    }

    /**
     * Moves the token 
     */
    public boolean move(int xPos, int yPos) {
        casillaPosible(xPos, yPos);
        casillasValidas();
        fichaEnCasillasValidas();
        boolean casillaValida = casillaValida(xPos, yPos);
        if (!casillaValida) {
            JOptionPane.showMessageDialog(null, "La casilla seleccionada no es válida para moverse.", "Casilla no válida", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!paredArriba(xPos, yPos) && !paredAbajo(xPos, yPos) && !paredDerecha(xPos, yPos) && !paredIzquierda(xPos, yPos)) {
            game.setElemento(fila, columna, 0);
            fila = xPos;
            columna = yPos;
            game.setElemento(fila, columna, 1);
            posicionesFicha.add(new int[]{xPos, yPos});
            return true;
        }
        return false;
    }
    

	private boolean casillaValida(int xPos, int yPos) {
    	boolean validate = false;
    	for (int[] coordenadas : casillasValidas) {
            if (coordenadas[0] == xPos && coordenadas[1] == yPos) {
                validate = true;
            }
        }
    	return validate;
    }
    

	private boolean casillaPosible(int xPos, int yPos) {
    	int distanciaFila = Math.abs(fila - xPos);
        int distanciaColumna = Math.abs(columna - yPos);
    	if(distanciaFila + distanciaColumna > 2||distanciaFila > 2 || distanciaColumna > 2) {
            return false;
    	}    
    	return true;
    }
    
    private void casillasValidas() {
    	casillasValidas.clear();
    	for(int i = 0; i < game.getSize(); i++) {
    		for(int j = 0; j < game.getSize(); j++) {
    			if(casillaPosible(i,j)) {
    				int[] coordenadas = {i,j};
    				casillasValidas.add(coordenadas);
    			}
    		}
    	}    		
    }
    
    private void fichaEnCasillasValidas() {
    	saltarFichaVerticalArribaAAbajo();
    	saltarFichaVerticalAbajoAArriba();
    	saltarFichasHorizontalIzquierdaADerecha();
    	saltarFichasHorizontalDerechaAIzquierda();
    }
    
    
    private void saltarFichaVerticalArribaAAbajo() {
        int[][] tablero = game.getTablero();
        List<int[]> coordenadasToAdd = new ArrayList<>();
        Iterator<int[]> iterator = casillasValidas.iterator();
        while (iterator.hasNext()) {
            int[] coordenadas = iterator.next();
            int filaC = coordenadas[0];
            int columnaC = coordenadas[1];
            if (tablero[filaC][columnaC] == 1) {
                if (filaC > fila && filaC != game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{filaC + 2, columnaC});
                    iterator.remove();
                } else if (filaC > fila && filaC == game.getSize() -1  && columna != 0 && columna != game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila + 2, columna - 2});
                    coordenadasToAdd.add(new int[]{fila + 2, columna + 2});
                    iterator.remove();
                } else if (filaC > fila && filaC == game.getSize() -1  && columna == 0) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna + 2});
                } else if (filaC > fila && filaC == game.getSize() -1  && columna == game.getSize() -1 ) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna - 2});
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }
    
    private void saltarFichaVerticalAbajoAArriba() {
        int[][] tablero = game.getTablero();
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
                } else if (filaC < fila && filaC == 0 && columna != 0 && columna != game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna - 2});
                    coordenadasToAdd.add(new int[]{fila - 2, columna + 2});
                    iterator.remove();
                } else if (filaC < fila && filaC == 0 && columna == 0) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna + 2});
                    iterator.remove();
                } else if (filaC < fila && filaC == 0 && columna == game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna - 2});
                    iterator.remove();
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }
    
    
    private void saltarFichasHorizontalIzquierdaADerecha() {
        int[][] tablero = game.getTablero();
        List<int[]> coordenadasToAdd = new ArrayList<>();
        Iterator<int[]> iterator = casillasValidas.iterator();
        while (iterator.hasNext()) {
            int[] coordenadas = iterator.next();
            int filaC = coordenadas[0];
            int columnaC = coordenadas[1];
            if (tablero[filaC][columnaC] == 1) {
                if (columnaC > columna && columnaC != game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{filaC, columnaC + 2});
                    iterator.remove();
                } else if (columnaC > columna && columnaC == game.getSize() -1  && fila != 0 && fila != game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna + 2});
                    coordenadasToAdd.add(new int[]{fila + 2, columna + 2});
                    iterator.remove();
                } else if (columnaC > columna && columnaC == game.getSize() -1  && fila == 0) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna + 2});
                } else if (columnaC > columna && columnaC == game.getSize() -1  && fila == game.getSize() -1 ) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila - 2, columna + 2});
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }

    private void saltarFichasHorizontalDerechaAIzquierda() {
        int[][] tablero = game.getTablero();
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
                } else if (columnaC < columna && columnaC == 0 && fila != 0 && fila != game.getSize() -1 ) {
                    coordenadasToAdd.add(new int[]{fila - 2, columna - 2});
                    coordenadasToAdd.add(new int[]{fila + 2, columna - 2});
                    iterator.remove();
                } else if (columnaC < columna && columnaC == 0 && fila == 0) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila + 2, columna - 2});
                } else if (columnaC < columna && columnaC == 0 && fila == game.getSize() -1 ) {
                    iterator.remove();
                    casillasValidas.add(new int[]{fila - 2, columna - 2});
                }
            }
        }
        casillasValidas.addAll(coordenadasToAdd);
    }
    
    /**
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean paredArriba(int xPos, int yPos) {
    	int[][] tablero = game.getTablero();
    	if( xPos < fila) {
    		if(tablero[xPos+1][yPos] == 3 || tablero[xPos+1][yPos] == 9 || tablero[xPos+1][yPos] == 8) {
            	JOptionPane.showMessageDialog(null, "No puede poner la ficha debido a que una pared enfrente suyo");
            	return true;
            }
    	}
    	return false;
    }
    
    
    private boolean paredAbajo(int xPos, int yPos) {
    	int[][] tablero = game.getTablero();
    	if( xPos > fila) {
    		if(tablero[xPos-1][yPos] == 3 || tablero[xPos-1][yPos] == 9 || tablero[xPos-1][yPos] == 8 ) {
            	JOptionPane.showMessageDialog(null, "No puede poner la ficha debido a que una pared enfrente suyo");
            	return true;
            }
    	}
    	return false;
    }
    
    private boolean paredDerecha(int xPos, int yPos) {
    	int[][] tablero = game.getTablero();
    	if(yPos > columna) {
    		if(tablero[xPos][yPos - 1] == 3 || tablero[xPos][yPos - 1] == 9 || tablero[xPos][yPos-1] == 8) {
    			JOptionPane.showMessageDialog(null, "No puede poner la ficha debido a que una pared enfrente suyo");
            	return true;
    		}
    	}
    	return false;
    }
    
    private boolean paredIzquierda(int xPos, int yPos) {
    	int[][] tablero = game.getTablero();
    	if(yPos < columna) {
    		if(tablero[xPos][yPos + 1] == 3 || tablero[xPos][yPos + 1] == 9 || tablero[xPos][yPos + 1] == 8) {
    			JOptionPane.showMessageDialog(null, "No puede poner la ficha debido a que una pared enfrente suyo");
            	return true;
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

