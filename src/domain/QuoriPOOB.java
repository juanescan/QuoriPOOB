package domain;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
    private int size;
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
	private int nWalls;
	private int normales;
	private int temporales;
	private int largas;
	private int aliadas;
	private int cNormales;
	private int cTeletransportador;
	private int cRegresar;
	private int cTurnoDoble;
	private List<int[]> posicionesCasillas;


  
/**
 * 
 * Constructor
 * @throws QuoriPOOBException 
 */
	private QuoriPOOB(Color player1Color , Color player2Color, String size, String temporales, 
			String largas, String aliadas, String cTeletransportador, String cRegresar, String cTurnoDoble)
					throws QuoriPOOBException{
		
		intSize(size);
		intTemporales(temporales);
		intLargas(largas);
		intAliadas(aliadas);
		intCTeletransportador(cTeletransportador);
		intCRegresar(cRegresar);
		intCTurnoDoble(cTurnoDoble);
        tablero = new int[this.size][this.size];
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
	
    
	public static synchronized QuoriPOOB getInstance(Color player1Color , Color player2Color, String size, String temporales, 
			String largas, String aliadas, String cTeletransportador, String cRegresar, String cTurnoDoble) throws QuoriPOOBException {
        if (instanciaUnica == null) {
            instanciaUnica = new QuoriPOOB(player1Color,player2Color,size,temporales,largas,aliadas
        			,cTeletransportador,cRegresar,cTurnoDoble);
        }
        return instanciaUnica;
    }
	
	
    /**
     * 2 = espacio para la pared, 4 = no se puede poner nada, 0 = casilla normal 
     */
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
     * inicializa las casillas del juego
     */
    private void inicializarCasillas() {
    	int nFilas = tablero.length;
    	int nColumnas = tablero.length;
    	posicionesCasillas = new ArrayList<>();
    	
    	for(int i = 0; i < nFilas;i++) {
    		for(int j = 0; j < nColumnas;j++) {
    			if(tablero[i][j] == 0) {
    				posicionesCasillas.add(new int[]{i, j});
    			}
    		}
    	}

    	Collections.shuffle(posicionesCasillas);
    	asignarDobleTurno(posicionesCasillas);
    	asignarCRegresar(posicionesCasillas);
    	asignarCNormales(posicionesCasillas);
    }
    /**
     * inicializa las fichas del juego
     */
    private void inicializarFichas() {
    	int mitad = (size -1) / 2;
        Color colorJugador1 = colorTokens.get(1);
        Color colorJugador2 = colorTokens.get(2);
        t1 = new Token(0, mitad, colorJugador1, this);
        tablero[0][mitad] = 1;
        tokens.put(1, t1);
        t2 = new Token(size-1, mitad, colorJugador2, this);
        tablero[size-1][mitad] = 1;
        tokens.put(2, t2);
    }
    
    

    /**
     * inicializa los jugadores del juego
     */
	private void inicializarJugadores() {
    	player1 = new Player("Jugador 1",t1, 10);
    	player2 = new Player("Jugador 2",t2, 10);
    	currentPlayer = player1;
    	}
    
    /**
     * cambia el turno cuando se realiza una accion
     */
    public void cambiaTurno() {
    	if(currentPlayer == player1) {
    		currentPlayer = player2;
    	}else if(currentPlayer == player2) {
    		currentPlayer = player1;
    	}
    }
    /**
     * verifica quien gana el juego
     */
    public boolean verificarVictoria() {
        Token t = currentPlayer.getToken();
        int fila = t.getFila();
        return (currentPlayer == player1 && fila == size-1) || (currentPlayer == player2 && fila == 0);
    }
    
    /**
     * mueve las fichas dependiendo de quien es el turno
     */
    public boolean move(int xPos, int yPos) throws QuoriPOOBException {
    	Token t = getCurrentPlayer().getToken();
    	boolean movHecho = t.move(xPos, yPos);
    	if(movHecho) {
    		Cell c = getCell(xPos,yPos);
    		c.act();
    	}
    	return movHecho;
    	
    } 	
    
    public void setElemento(int fila, int columna, int valor){
        tablero[fila][columna] = valor;
    }
    /**
     * coloca una barrera en una posicion deseada
     */
    public void putWall(boolean horizontal,int xPos, int yPos, String tipo) {
    	if(currentPlayer.getNWalls() > 0) {
    		try {
    			Class<?> wallClass = Class.forName("domain." + tipo);
                Constructor<?> constructor = wallClass.getConstructor(boolean.class, int.class, int.class);
                Wall wallInstance = (Wall) constructor.newInstance(horizontal, xPos, yPos);
                wallInstance.put();
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}else {
    		JOptionPane.showMessageDialog(null, "El jugador " + currentPlayer.getName() + " ya no tiene paredes que poner");
    	}
    }
    /**
     * guarda la partida
     */
    public void save(File archivo) {
    	 try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivo))) {
             outputStream.writeObject(this); 
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    // Método para abrir un objeto QuoriPOOB desde un archivo
    public static QuoriPOOB open(File archivo) {
    	QuoriPOOB quoriPOOB = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivo))) {
            quoriPOOB = (QuoriPOOB) inputStream.readObject(); 
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return quoriPOOB;
        
    }
    
    
    public int getSize() {
		return size;
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
	

	
	private void intSize(String size) throws QuoriPOOBException {
		try {
			int tempSize = Integer.parseInt(size);
			if(tempSize < 3) {
				throw new QuoriPOOBException(QuoriPOOBException.TAMANO_MINIMO_TABLERO);
			}else if (tempSize % 2 == 0) {
				throw new QuoriPOOBException(QuoriPOOBException.TAMANO_PAR_TABLERO);
			}
			this.size = (tempSize * 2) -1;
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el tamaño del tablero");
			intSize(n);
		}
	}
	
	private void intTemporales(String temporales) {
		try {
			this.temporales = Integer.parseInt(temporales);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el numero de paredes temporales");
			intTemporales(n);
		}
	}
	
	private void intLargas(String largas) {
		try {
			this.largas = Integer.parseInt(largas);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el numero de paredes largas");
			intLargas(n);
		}
	}
	
	private void intAliadas(String aliadas) {
		try {
			this.aliadas = Integer.parseInt(aliadas);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el numero de paredes aliadas");
			intAliadas(n);
		}
	}
	
	private void intCTeletransportador(String cTeletransportador) {
		try {
			this.cTeletransportador = Integer.parseInt(cTeletransportador);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el numero de casillas teletransportadoras");
			intCTeletransportador(n);
		}
	}
	
	private void intCRegresar(String cRegresar) {
		try {
			this.cRegresar = Integer.parseInt(cRegresar);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el numero de casillas teletransportadoras");
			intCRegresar(n);
		}
	}
	
	private void intCTurnoDoble(String cTurnoDoble) {
		try {
			this.cTurnoDoble = Integer.parseInt(cTurnoDoble);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el numero de casillas teletransportadoras");
			intCTurnoDoble(n);
		}
	}
	
	
	/**
	 * 5 = casilla de doble turno
	 */
	private void asignarDobleTurno(List<int[]> posicionesCasillas) {
		for (int i = 0; i < cTurnoDoble; i++) {
	        if (i < posicionesCasillas.size()) {
	            int[] pos = posicionesCasillas.get(i);
	            posicionesCasillas.remove(i);
	            DoubleTurnCell doubleTurnCell = new DoubleTurnCell(pos[0], pos[1]);
	            Cells.add(doubleTurnCell);
	            tablero[pos[0]][pos[1]] = 5; 
	        }
	    }
	}
	/**
	 * casilla para regresar
	 */
	private void asignarCRegresar(List<int[]> posicionesCasillas) {
		for (int i = 0; i < cTurnoDoble; i++) {
	        if (i < posicionesCasillas.size()) {
	            int[] pos = posicionesCasillas.get(i);
	            posicionesCasillas.remove(i);
	            ReturnCell returnCell = new ReturnCell(pos[0], pos[1]);
	            Cells.add(returnCell);
	            tablero[pos[0]][pos[1]] = 7; 
	        }
	    }
	}
	/**
	 * casillas normales
	 */
	private void asignarCNormales(List<int[]> posicionesCasillas) {
		while (!posicionesCasillas.isEmpty()) {
	        int[] pos = posicionesCasillas.remove(0);
	        NormalCell normalCell = new NormalCell(pos[0], pos[1]);
	        Cells.add(normalCell);
	        tablero[pos[0]][pos[1]] = 0; 
	    }
	}
	/**
	 * reinicia el tablero, casillas, fichas y jugadores
	 */
	public void reset() {
		inicializarTablero();
        inicializarCasillas();
        inicializarFichas();
        inicializarJugadores();
	}
	
	/**
	 * obtiene una casilla dada una fila y una columna
	 */
	public Cell getCell(int fila, int columna) {
		for(Cell cell : Cells) {
			if(cell.getFila() == fila && cell.getColumna() == columna) {
				return cell;
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
	
	public int getnWalls() {
		return nWalls;
	}


	public void setnWalls(int nWalls) {
		this.nWalls = nWalls;
	}


	public int getNormales() {
		return normales;
	}


	public void setNormales(int normales) {
		this.normales = normales;
	}


	public int getTemporales() {
		return temporales;
	}


	public void setTemporales(int temporales) {
		this.temporales = temporales;
	}


	public int getLargas() {
		return largas;
	}


	public void setLargas(int largas) {
		this.largas = largas;
	}


	public int getAliadas() {
		return aliadas;
	}


	public void setAliadas(int aliadas) {
		this.aliadas = aliadas;
	}


	public int getcNormales() {
		return cNormales;
	}


	public void setcNormales(int cNormales) {
		this.cNormales = cNormales;
	}


	public int getcTeletransportador() {
		return cTeletransportador;
	}


	public void setcTeletransportador(int cTeletransportador) {
		this.cTeletransportador = cTeletransportador;
	}


	public int getcRegresar() {
		return cRegresar;
	}


	public void setcRegresar(int cRegresar) {
		this.cRegresar = cRegresar;
	}


	public int getcTurnoDoble() {
		return cTurnoDoble;
	}


	public void setcTurnoDoble(int cTurnoDoble) {
		this.cTurnoDoble = cTurnoDoble;
	}


	public Token getT1() {
		return t1;
	}

	public Token getT2() {
		return t2;
	}


	public Map<Integer, Color> getColorTokens() {
		return colorTokens;
	}
	
	
}