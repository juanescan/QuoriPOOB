package domain;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
/**
 * prueba
 */
public class QuoriPOOB {
    private int size = 17;
    private int[][] tablero;
    private Map<Integer,Token> tokens;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Token t1;
    private Token t2;


  

	public QuoriPOOB(){
        tablero = new int[size][size];
        tokens = new HashMap<>();
        inicializarTablero();
        inicializarJugadores();

    }
    
    private void inicializarTablero(){
        for (int i = 0; i < tablero.length; i++){
            for (int j = 0; j < tablero[i].length; j++){
            	if(i % 2 != 0 || j % 2 != 0) {
            		tablero[i][j] = 2;
            	}else {
            		tablero[i][j] = 0;
            	}
            }
        }
        inicializarFichas();
    }
    
    private void inicializarFichas() {
    	t1 = new Token(0,8,Color.blue,this);
    	tablero[0][8] = 1;
    	tokens.put(1,t1);
    	t2 = new Token(16,8,Color.red,this);
    	tablero[16][8] = 1;
    	tokens.put(2,t2);
    	
    }
    
    private void inicializarJugadores() {
    	player1 = new Player("Jugador 1",t1, 10);
        player2 = new Player("Jugador 2",t2, 10);
        currentPlayer = player1;
    }
    
    
    public void cambiaTurno() {
    	if(currentPlayer == player1) {
    		currentPlayer = player2;
    	}else if(currentPlayer == player2) {
    		currentPlayer = player1;
    	}
    }
    
    private void verificarVictoria() {
    	
    }
    
    
    public void move(int xPos, int yPos) {
    	Token t = getCurrentPlayer().getToken();
    	t.move(xPos, yPos);
    }
    
    public void setElemento(int fila, int columna, int valor){
        tablero[fila][columna] = valor;
    }
    
    public void putWall(boolean direction,int xPos, int yPos) {
    	
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
	
	public Token getToken(int fila, int columna) {
	    for (Token token : tokens.values()) {
	        if (token.getFila() == fila && token.getColumna() == columna) {
	            return token;
	        }
	    }
	    return null; // Retorna null si no se encuentra ningún token en las coordenadas especificadas
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	
}