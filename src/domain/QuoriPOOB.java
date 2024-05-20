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
/**
 * 
 */
public class QuoriPOOB  implements Serializable {
	private static QuoriPOOB instanciaUnica;
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
	private Object gameMode;



  
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
	
    
	public static synchronized QuoriPOOB obtenerInstancia(Color player1Color, Color player2Color) {
        if (instanciaUnica == null) {
            instanciaUnica = new QuoriPOOB(player1Color, player2Color);
        }
        return instanciaUnica;
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
    
    /**
     * sdkaop
     */
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
        return (currentPlayer == player1 && fila == 16) || (currentPlayer == player2 && fila == 0);
    }
    
    
    public boolean move(int xPos, int yPos) {
    	Token t = getCurrentPlayer().getToken();
    	return t.move(xPos, yPos);
    
    } 	
    
    public void setElemento(int fila, int columna, int valor){
        tablero[fila][columna] = valor;
    }
    
    public void putWall(boolean horizontal,int xPos, int yPos) {
    	if(currentPlayer.getNWalls() > 0) {
    		if(horizontal) {
        		if(yPos <= 14) {
        			paredHorizontal1(xPos, yPos);
        		}else if(yPos > 14) {
        			paredHorizontal2(xPos, yPos);
        		}
        	}else if(!horizontal) {
        		if(xPos <= 14) {
        			paredVertical1(xPos,yPos);
        		}else if( xPos > 14) {
        			paredVertical2(xPos,yPos);
        		}
        	}
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
	
	private void paredHorizontal1(int xPos, int yPos) {
		if(!hayPared(xPos,xPos,yPos,yPos+2)) {
			Wall w = new Wall(true,xPos,xPos,yPos,yPos+2);
			tablero[xPos][yPos] = 3;
			tablero[xPos][yPos+1] = 3;
			tablero[xPos][yPos+2] = 3;
			currentPlayer.minusNWalls();
			cambiaTurno();
		}
	}
	
	private void paredHorizontal2(int xPos, int yPos) {
		if(!hayPared(xPos,xPos,yPos,yPos-2)) {
			Wall w = new Wall(true,xPos,xPos,yPos,yPos-2);
			tablero[xPos][yPos] = 3;
			tablero[xPos][yPos-1] = 3;
			tablero[xPos][yPos-2] = 3;
			currentPlayer.minusNWalls();
			cambiaTurno();
		}
	}
	
	private void paredVertical1(int xPos, int yPos) {
		if(!hayPared(xPos,xPos+2,yPos,yPos)) {
			Wall w = new Wall(false,xPos,xPos+2,yPos,yPos);
			tablero[xPos][yPos] = 3;
			tablero[xPos+1][yPos] = 3;
			tablero[xPos+2][yPos] = 3;
			currentPlayer.minusNWalls();
			cambiaTurno();
		}
	}
	
	private void paredVertical2(int xPos, int yPos) { 
		if(!hayPared(xPos,xPos-2,yPos,yPos)){
			Wall w = new Wall(false,xPos,xPos-2,yPos,yPos);
			tablero[xPos][yPos] = 3;
			tablero[xPos-1][yPos] = 3;
			tablero[xPos-2][yPos] = 3;
			currentPlayer.minusNWalls();
			cambiaTurno();
		}
	}
	
	private boolean hayPared(int xPos1, int xPos2, int yPos1, int yPos2) {
		if(tablero[xPos1][yPos1] == 3 || tablero[xPos2][yPos2] == 3 ) {
			JOptionPane.showMessageDialog(null, "No se puede colocar la pared debido a que ya hay otra pared");
			return true;
		}
		return false;
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