package domain;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
/**
 * prueba
 */
public class QuoriPOOB  implements Serializable {
    private int size = 17;
    private int[][] tablero;
    private Map<Integer,Token> tokens;
    private Map<Integer,Color> colorTokens;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Token t1;
    private Token t2;
    private List<Cell> Cells;



  
/**
 * 
 * Constructor
 */
	public QuoriPOOB(Color player1Color , Color player2Color){
        tablero = new int[size][size];
        tokens = new HashMap<>();
        Cells = new ArrayList<>();
        colorTokens = new HashMap<>();
        colorTokens.put(1, player1Color);
        colorTokens.put(2, player2Color);
        inicializarTablero();
        inicializarCasillas();
        inicializarFichas();
        inicializarJugadores();

    }
    
    private void inicializarTablero(){
        for (int i = 0; i < tablero.length; i++){
            for (int j = 0; j < tablero[i].length; j++){
            	if(i % 2 != 0 || j % 2 != 0) {
            		tablero[i][j] = 2;
            	}else if(i % 2 != 0 && j % 2 != 0) {
            		tablero[i][j] = 4;
            	}else {
            		tablero[i][j] = 0;
            	}
            }
        }
    }
    
    private void inicializarCasillas() {
    	int nFilas = tablero.length;
    	int nColumnas = tablero.length;
    	for(int i = 0; i < nFilas;i++) {
    		for(int j = 0; j < nColumnas;j++) {
    			if(tablero[i][j] == 0) {
    				NormalCell normalCell = new NormalCell(i, j); 
                    Cells.add(normalCell);
    			}
    		}
    	}
    }
    
    private void inicializarFichas() {
        Color colorJugador1 = colorTokens.get(1);
        Color colorJugador2 = colorTokens.get(2);
        t1 = new Token(0, 8, colorJugador1, this);
        tablero[0][8] = 1;
        tokens.put(1, t1);
        t2 = new Token(16, 8, colorJugador2, this);
        tablero[16][8] = 1;
        tokens.put(2, t2);
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
        	
    	}else {
    		JOptionPane.showMessageDialog(null, "El jugador " + currentPlayer.getName() + " ya no tiene paredes que poner");
    	}
    	
    }
    
    public void save(File archivo) {
        
    }

    // Método para abrir un objeto QuoriPOOB desde un archivo
    public static QuoriPOOB open(File archivo) {
		return null;
        
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
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}

	
}