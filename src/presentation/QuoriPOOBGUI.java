package presentation;

import domain.*;
import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Map;

/*
 * Represents the QuoriPOOBGUI game
 * version 1.0
 * Santiago Córdoba- Juan Cancelado
 */
public class QuoriPOOBGUI extends JFrame{

	private int size;
    private JMenuBar barra;
    private JMenu menu;
    private JMenu terminar;
    private JMenuItem abrir;
    private JMenuItem salir;
    private JMenuItem salvar;
    private JMenuItem pantalla_Inicio;
    private JMenuItem reiniciar;
    private JFileChooser fileChooser;
    private JButton[][] casillas;
    private JPanel initialScreen;
    private JPanel configurationScreen;
    private JButton inicio;
    private JButton inicioC;
    private JButton salirB;
    private JPanel main;
    private QuoriPOOB game;
    private int[][] tablero;
    private JLabel paredesLabel1;
    private JLabel paredesLabel2;
    private JComboBox<String> modoDeJuego;
    private JComboBox<String>tipoDeJuego;
    private JLabel jugador1Turno;
    private JLabel jugador2Turno;

    

    public QuoriPOOBGUI() {
        prepareElements();
        prepareInitialScreen();
        prepareElementsMenu();
        prepareActions();
    }

 
    
    
    private void prepareInitialScreen(){
        initialScreen = new JPanel();
        initialScreen.setLayout(new BoxLayout(initialScreen, BoxLayout.Y_AXIS));
        initialScreen.setBackground(Color.ORANGE);

        JLabel texto = new JLabel("QuoriPOOB");
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font = new Font("Arial", Font.BOLD, 24);
        texto.setFont(font);
        initialScreen.add(texto);
        initialScreen.add(Box.createVerticalStrut(100));

        inicio = new JButton("Inicio");
        inicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialScreen.add(inicio);
        initialScreen.add(Box.createVerticalStrut(40));

        salirB = new JButton("Salir");
        salirB.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialScreen.add(salirB);
        add(initialScreen);
        setLocationRelativeTo(null);

    }
    
    private void prepareConfigurationScreen() {
        configurationScreen = new JPanel();
        configurationScreen.setLayout(new BoxLayout(configurationScreen, BoxLayout.Y_AXIS));
        configurationScreen.setBackground(Color.ORANGE);
        JLabel textoSeleccionarModoJuego = new JLabel("Seleccione el modo de juego");
        modoDeJuego = new JComboBox<>(new String[]{"Jugador vs Jugador", "Jugador vs Maquina"});
        modoDeJuego.setMaximumSize(new Dimension(230, 50));
        textoSeleccionarModoJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        modoDeJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textoSeleccionarTipoDeJuego = new JLabel("Seleccione el tipo de juego");
        tipoDeJuego = new JComboBox<>(new String[]{"Normal", "Contrarreloj", "Cronometrado"});
        tipoDeJuego.setMaximumSize(new Dimension(230, 50));
        textoSeleccionarTipoDeJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipoDeJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        inicioC = new JButton("Inicio");
        inicioC.setAlignmentX(Component.CENTER_ALIGNMENT);
        configurationScreen.add(textoSeleccionarModoJuego);
        configurationScreen.add(Box.createVerticalStrut(10)); 
        configurationScreen.add(modoDeJuego);
        configurationScreen.add(Box.createVerticalStrut(40)); 
        configurationScreen.add(textoSeleccionarTipoDeJuego);
        configurationScreen.add(Box.createVerticalStrut(10)); 
        configurationScreen.add(tipoDeJuego);
        configurationScreen.add(Box.createVerticalStrut(40));
        configurationScreen.add(inicioC);
        prepareActionsConfiguration();
        add(configurationScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }




    private void prepareElements(){
        setTitle("QuoriPOOBGUI");
        int size = 600;
        setSize(size, size);
        setLocationRelativeTo(null); 
        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
    }



    private void prepareElementsMenu(){
        barra = new JMenuBar();
        menu = new JMenu("Archivos");
        terminar = new JMenu("Terminar");
        abrir= new JMenuItem("abrir");
        salvar = new JMenuItem("salvar");
        salir = new JMenuItem("salir");
        pantalla_Inicio = new JMenuItem("pantalla inicio");
        reiniciar = new JMenuItem("reiniciar");

        setJMenuBar(barra);
        barra.add(menu);
        barra.add(terminar);
        menu.add(abrir);
        menu.add(salvar);
        menu.add(salir);
        terminar.add(pantalla_Inicio);
        terminar.add(reiniciar);
        terminar.add(salir);
    }

    private void prepareElementsBoard(String playerName1,String playerName2,Color player1Color,Color player2Color){
        main = new JPanel();
        main.setLayout(new BorderLayout());
        jugador1(main,playerName1,player1Color);
        tableroPanel(main);
        jugador2(main,playerName2,player2Color);
        add(main);
        prepareActionsBoard();
    }

    private void prepareActions(){
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                actionClose();
            }
        });

        salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                actionClose();
            }
        });

        abrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                try {
					actionOpen();
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
            }
        });

        salvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                actionSave();
            }
        });


        inicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		actionConfiguration();
        	}
        });
        
        reiniciar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		try {
					resetGame();
				} catch (QuoriPOOBException e) {
					e.printStackTrace();
				}
        	}
        });
        
        pantalla_Inicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		actionInitialScreen();
        	}
        });
        
        salirB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		actionClose();
        	}
        });



    }


    private void actionClose(){
        int confirmado = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit");
        if(confirmado == JOptionPane.YES_OPTION){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        }else{
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    private void actionOpen() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String filePath = archivo.getAbsolutePath();
            QuoriPOOB g = QuoriPOOB.open(archivo); // Cambia QuoriPOOB.open a lo que sea que uses para abrir archivos
			game = g;
			repintar();
			paintFichas();
			paintWall();
        }
    }



    private void actionSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        int sel = fileChooser.showSaveDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String filePath = archivo.getAbsolutePath();
            game.save(archivo);
			JOptionPane.showMessageDialog(null, "Se ha guardado exitosamente en: " + filePath);
        }
    }
    
    private void actionNew() throws QuoriPOOBException {
    	
        ingresarDatos();
        remove(configurationScreen);
        repaint();
        revalidate();
    }
    
    private void actionConfiguration() {
    	prepareConfigurationScreen();
    	remove(initialScreen);
    	repaint();
    	revalidate();
    }
    
    private void actionInitialScreen() {
    	remove(main);
    	prepareInitialScreen();
    	prepareElementsMenu();
    	prepareActions();
    	repaint();
    	revalidate();
    }
    
    
    private void prepareActionsConfiguration() {
    	inicioC.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		try {
					actionNew();
				} catch (QuoriPOOBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
    }
    
    
    private void prepareActionsBoard(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                final JButton currentButton = casillas[i][j];
                final int x = i; 
                final int y = j; 
                currentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev){
                        if(x % 2 != 0 || y % 2 != 0) {
                            actionWall(currentButton);
                        }
                        else {
                            try {
								actionCell(currentButton);
							} catch (QuoriPOOBException e) {
								e.printStackTrace();
							}
                        }
                    }
                });
            }
        }
    }







    private void jugador2(JPanel mainPanel, String playerName2, Color player2Color) {
        JPanel jugador2Panel = new JPanel();
        jugador2Panel.setLayout(new BorderLayout());
        jugador2Panel.setPreferredSize(new Dimension(140, mainPanel.getHeight()));
        jugador2Turno = new JLabel("");
        jugador2Panel.add(jugador2Turno);
        JLabel jugador2Label = new JLabel(playerName2, SwingConstants.CENTER);
        jugador2Label.setFont(new Font("Arial", Font.BOLD, 16)); 
        jugador2Label.setBackground(player2Color);
        jugador2Label.setOpaque(true);
        jugador2Panel.add(jugador2Label, BorderLayout.NORTH);
        JPanel datosJugador2Panel = new JPanel();
        datosJugador2Panel.setLayout(new GridLayout(2, 1));
        jugador2Turno = new JLabel("",SwingConstants.CENTER);
        datosJugador2Panel.add(jugador2Turno);
        paredesLabel2 = new JLabel("Paredes: " + game.getPlayer2().getNWalls(), SwingConstants.CENTER);
        datosJugador2Panel.add(paredesLabel2);
        jugador2Panel.add(datosJugador2Panel, BorderLayout.CENTER);
        mainPanel.add(jugador2Panel, BorderLayout.EAST);
    }


    private void tableroPanel(JPanel mainPanel){
    	this.size = game.getSize();
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(size,size));
        casillas = new JButton[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                JButton casilla = new JButton();
                if(i % 2 != 0 && j % 2 != 0) {
                	casilla.setBackground(Color.black);
                }else if(i % 2 != 0 || j % 2 != 0) {
                	casilla.setBackground(Color.lightGray);
                }
                else if(tablero[i][j] == 0){
                	casilla.setBackground(Color.white);
                }else if(tablero[i][j] == 5) {
                	Color paleGreen = new Color(152, 251, 152);
                	casilla.setBackground(paleGreen);
                }else if(tablero[i][j] == 7) {
                	Color rojoPalido = new Color(255, 192, 192);
                	casilla.setBackground(rojoPalido);
                }
                casilla.putClientProperty("fila", i);
                casilla.putClientProperty("columna", j);
                casillas[i][j] = casilla;
                boardPanel.add(casilla);
            }
        }
        paintFichas();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
    }
    
    private void repintar() {
    	for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
            	if(i % 2 != 0 && j % 2 != 0) {
                	casillas[i][j].setBackground(Color.black);
                }else if(i % 2 != 0 || j % 2 != 0) {
                	casillas[i][j].setBackground(Color.lightGray);
                }
                else if(tablero[i][j] == 0){
                	casillas[i][j].setBackground(Color.white);
                }else if(tablero[i][j] == 5) {
                	Color paleGreen = new Color(152, 251, 152);
                	casillas[i][j].setBackground(paleGreen);
                }else if(tablero[i][j] == 7) {
                	Color rojoPalido = new Color(255, 192, 192);
                	casillas[i][j].setBackground(rojoPalido);
                }
            }
    	}
    }
    
    private void paintFichas() {
    	for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
            	if(tablero[i][j] == 0) {
                	casillas[i][j].setBackground(Color.white);
            	}
            }
        }
        Token t1 = game.getT1();
        Token t2 = game.getT2();
        Color c1 = t1.getColor();
        Color c2 = t2.getColor();
        casillas[t1.getFila()][t1.getColumna()].setBackground(c1);
        casillas[t2.getFila()][t2.getColumna()].setBackground(c2);
        
        revalidate();
        repaint();
    }
    
    
  

    private void jugador1(JPanel mainPanel, String playerName1, Color player1Color) {
        JPanel jugador1Panel = new JPanel();
        jugador1Panel.setLayout(new BorderLayout());
        jugador1Panel.setPreferredSize(new Dimension(140, mainPanel.getHeight()));
        JLabel jugador1Label = new JLabel(playerName1, SwingConstants.CENTER);
        jugador1Label.setFont(new Font("Arial", Font.BOLD, 16)); 
        jugador1Label.setBackground(player1Color);
        jugador1Label.setOpaque(true);
        jugador1Panel.add(jugador1Label, BorderLayout.NORTH);
        JPanel datosJugador1Panel = new JPanel();
        datosJugador1Panel.setLayout(new GridLayout(2, 1));
        jugador1Turno = new JLabel("",SwingConstants.CENTER);
        datosJugador1Panel.add(jugador1Turno);
        paredesLabel1 = new JLabel("Paredes: " + game.getPlayer1().getNWalls(), SwingConstants.CENTER);
        datosJugador1Panel.add(paredesLabel1);
        jugador1Panel.add(datosJugador1Panel, BorderLayout.CENTER);
        mainPanel.add(jugador1Panel, BorderLayout.WEST);
    }


    private void actionCell(JButton button) throws QuoriPOOBException {
        int fila = (int) button.getClientProperty("fila");
        int columna = (int) button.getClientProperty("columna");
        boolean moveSuccessful = game.move(fila, columna);
        if (moveSuccessful) {
            paintFichas();
            boolean verify = game.verificarVictoria();
            if (verify) {
                int confirmado = JOptionPane.showConfirmDialog(this, "El jugador " + game.getCurrentPlayer().getName() + " ha ganado el juego. ¿Quieres salir del juego?", "Victoria", JOptionPane.YES_NO_OPTION);
                if (confirmado == JOptionPane.YES_OPTION) {
                    actionClose();
                } else {
                    actionInitialScreen();
                }
            } else {
            	game.cambiaTurno();
                actualizarTurno();
            }
        }
    }
    
    private void actionWall(JButton button) {
    	int fila = (int)button.getClientProperty("fila");
        int columna = (int)button.getClientProperty("columna");
        if(fila % 2 != 0 && columna % 2 != 0) {
        	JOptionPane.showMessageDialog(null,"En este sitio no se puede poner una pared");
        }else if(fila % 2 != 0) {
        	String tipo = JOptionPane.showInputDialog("Ingrese el tipo de pared que desea poner");
        	game.putWall(true, fila, columna, tipo);
        }else if(columna % 2 != 0) {
        	String tipo = JOptionPane.showInputDialog("Ingrese el tipo de pared que desea poner");
        	game.putWall(false, fila, columna, tipo);
        }

        paintWall();
        refresh();
        
        actualizarTurno();
    }
    
    
    private void resetGame() throws QuoriPOOBException {
    	
    	game.reset();
    	repintar();
        paintFichas();
        refresh();
    }
    
    private void paintWall() {
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(tablero[i][j] == 3) {
    				Color brown = new Color(139, 69, 19);
    				casillas[i][j].setBackground(brown);
    			}else if(tablero[i][j] == 9) {
    				Color opaqueOrange = new Color(255, 165, 128);
    				casillas[i][j].setBackground(opaqueOrange);
    			}else if(tablero[i][j] == 8) {
    				Color darkBlue = new Color(0, 0, 139);
    				casillas[i][j].setBackground(darkBlue);
    			}
    		}
    	}
    }
    
    private void refresh() {
    	 paredesLabel1.setText("Paredes: " + game.getPlayer1().getNWalls());
    	 paredesLabel2.setText("Paredes: " + game.getPlayer2().getNWalls());
    }
    
    private void actualizarTurno() {
        if (game.getCurrentPlayer() == game.getPlayer1()) {
            jugador1Turno.setText("Es tu turno");  
            jugador2Turno.setText(""); 
        } else if (game.getCurrentPlayer() == game.getPlayer2()) {
            
        	jugador2Turno.setText("Es tu turno");  
            jugador1Turno.setText(""); 
        }
    }

    private void ingresarDatos() throws QuoriPOOBException {
    	String playerName1 = JOptionPane.showInputDialog(this, "Ingrese el nombre del jugador 1:");
    	String playerName2 = JOptionPane.showInputDialog(this, "Ingrese el nombre del jugador 2:");
    	Color player1Color = JColorChooser.showDialog(this, "Seleccione el color de la ficha para " + playerName1, Color.RED);
    	Color player2Color = JColorChooser.showDialog(this, "Seleccione el color de la ficha para " + playerName2, Color.BLUE);
    	String size = JOptionPane.showInputDialog(this, "Ingrese el tamaño del tablero: ");
    	String temporales = JOptionPane.showInputDialog(this, "Ingrese el numero de paredes temporales: ");
    	String largas = JOptionPane.showInputDialog(this, "Ingrese el numero de paredes largas: ");
    	String aliadas = JOptionPane.showInputDialog(this, "Ingrese el numero de paredes aliadas: ");
    	String cTeletransportador = JOptionPane.showInputDialog(this, "Ingrese el numero de casillas teletransportadoras: ");
    	String cRegresar = JOptionPane.showInputDialog(this, "Ingrese el numero de casillas regresadoras: ");
    	String cTurnoDoble = JOptionPane.showInputDialog(this, "Ingrese el numero de casillas de turno doble: ");
    	game = QuoriPOOB.getInstance(player1Color,player2Color,size,temporales,largas,aliadas
    			,cTeletransportador,cRegresar,cTurnoDoble);
    	tablero = game.getTablero();
        prepareElementsBoard(playerName1,playerName2,player1Color,player2Color);
    }

    public static void main(String[] args){
        JFrame frame = new QuoriPOOBGUI();
        frame.setVisible(true);
    }
    
}