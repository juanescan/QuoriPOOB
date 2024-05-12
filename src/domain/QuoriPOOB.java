package domain;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
/**
 * prueba
 */
public class QuoriPOOB implements Serializable {
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
        inicializarFichas();
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
    
    public boolean verificarVictoria() {
    	Token t = currentPlayer.getToken();
    	int fila = t.getFila();
    	if(currentPlayer == player1 && fila == 16 || currentPlayer == player2 && fila == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    public void move(int xPos, int yPos) {
    	Token t = getCurrentPlayer().getToken();
    	t.move(xPos, yPos);
    } 	
    
    public void setElemento(int fila, int columna, int valor){
        tablero[fila][columna] = valor;
    }
    
    public void putWall(boolean horizontal,int xPos, int yPos) {
    	if(currentPlayer.getNWalls() > 0) {
    		if(horizontal) {
        		if(yPos < 14) {
        			Wall w = new Wall(true,xPos,xPos+2,yPos,yPos);
        			tablero[xPos][yPos] = 3;
        			tablero[xPos][yPos+1] = 3;
        			tablero[xPos][yPos+2] = 3;
        		}else if(yPos > 14) {
        			Wall w = new Wall(true,xPos,xPos-2,yPos,yPos);
        			tablero[xPos][yPos] = 3;
        			tablero[xPos][yPos-1] = 3;
        			tablero[xPos][yPos-2] = 3;
        		}
        	}else if(!horizontal) {
        		if(xPos < 14) {
        			Wall w = new Wall(false,xPos,xPos,yPos,yPos+2);
        			tablero[xPos][yPos] = 3;
        			tablero[xPos+1][yPos] = 3;
        			tablero[xPos+2][yPos] = 3;
        		}else if( xPos > 14) {
        			Wall w = new Wall(false,xPos,xPos,yPos,yPos-2);
        			tablero[xPos][yPos] = 3;
        			tablero[xPos-1][yPos] = 3;
        			tablero[xPos-2][yPos] = 3;
        		}
        	}
        	currentPlayer.minusNWalls();
        	cambiaTurno();
    	}
    	
    }
    
    public static QuoriPOOB open(File archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            QuoriPOOB game = (QuoriPOOB) in.readObject();
            game.tokens = new HashMap<>();
            for (Token token : game.tokens.values()) {
                int fila = token.getFila();
                int columna = token.getColumna();
                game.tablero[fila][columna] = 1; // Actualiza el tablero con las posiciones de las fichas
            }
            return game;
        }
    }

    
    public void save(File archivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(this);
        }
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
	    return null; 
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	
}