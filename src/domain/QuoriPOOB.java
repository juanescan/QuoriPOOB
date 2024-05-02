package domain;

import java.util.Map;
/**
 * prueba
 */
public class QuoriPOOB {
    private int size = 9;
    private int[][] tablero;
    private Map<Integer,Token> tokens;


  

	public QuoriPOOB(){
        tablero = new int[size][size];
        inicializarTablero();

    }
    
    public void inicializarTablero(){
        for (int i = 0; i < tablero.length; i++){
            for (int j = 0; j < tablero[i].length; j++){
                tablero[i][j] = 0;
            }
        }
    }
    
    public void inicializarFichas() {
    	for(int i = 0; i < 2; i++) {
    		
    	}
    }
    
    public void setElemento(int fila, int columna, int valor){
        tablero[fila][columna] = valor;
    }
    
    
    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] getTablero() {
		return tablero;
	}


	public Map<Integer, Token> getTokens() {
		return tokens;
	}

	
}