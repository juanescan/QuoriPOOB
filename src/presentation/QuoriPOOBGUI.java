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
 * Represents the QuoriPOOBGUI QuoriPOOB.getInstance()
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
    private JComboBox<String> nivelDeDificultad;
    private JButton inicio;
    private JButton inicioC;
    private JButton salirB;
    private JPanel main;
    private int[][] tablero;
    private JLabel paredesLabel1;
    private JLabel paredesLabel2;
    private JLabel normales1;
    private JLabel normales2;
    private JLabel aliadas1;
    private JLabel aliadas2;
    private JLabel temporales1;
    private JLabel temporales2;
    private JLabel largas1;
    private JLabel largas2;
    private JComboBox<String> modoDeJuego;
    private JComboBox<String>tipoDeJuego;
    private JLabel jugador1Turno;
    private JLabel jugador2Turno;
    private JLabel textoSeleccionarNivelDeDificultad;
    private int tiempoJuegoContrarreloj;
    private Contador contadorJugador1;
    private Contador contadorJugador2;
    private String modoSeleccionado;
    private String tipoSeleccionado;
    private String nivelSeleccionado; 
    

    

    /**
     * begin for the JFrame
     */
    public QuoriPOOBGUI() {
        prepareElements();
        prepareInitialScreen();
        prepareElementsMenu();
        prepareActions();
    }

 
    
    
    /**
     * prepare elements for the initial screen
     */
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
    
    /**
     * prepare elements for the configuration
     */
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
        textoSeleccionarNivelDeDificultad = new JLabel("Seleccione el nivel de dificultad");
        textoSeleccionarNivelDeDificultad.setVisible(false);
        nivelDeDificultad = new JComboBox<>(new String[]{"Principiante", "Intermedio", "Avanzado"});
        nivelDeDificultad.setMaximumSize(new Dimension(230, 50));
        nivelDeDificultad.setAlignmentX(Component.CENTER_ALIGNMENT);
        nivelDeDificultad.setVisible(false); 
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
        configurationScreen.add(textoSeleccionarNivelDeDificultad);
        configurationScreen.add(Box.createVerticalStrut(10));
        configurationScreen.add(nivelDeDificultad);
        configurationScreen.add(Box.createVerticalStrut(40));
        configurationScreen.add(inicioC);

        prepareActionsConfiguration();
        add(configurationScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    /**
     * prepare the title of game
     */
    private void prepareElements(){
        setTitle("QuoriPOOBGUI");
        int size = 600;
        setSize(size, size);
        setLocationRelativeTo(null); 
        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
    }


    /**
     * prepare elements for the menu bar
     */
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

    /**
     * prepare elements for the game screen
     * @param playerName1
     * @param playerName2
     * @param player1Color
     * @param player2Color
     */
    private void prepareElementsBoard(String playerName1,String playerName2,Color player1Color,Color player2Color){
        main = new JPanel();
        main.setLayout(new BorderLayout());
        jugador1(main,playerName1,player1Color);
        tableroPanel(main);
        jugador2(main,playerName2,player2Color);
        add(main);
        prepareActionsBoard();
    }

    /**
     * prepare actions for the menu bar and initial screen
     */
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


    /**
     * action to close the game
     */
    public void actionClose(){
        int confirmado = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit");
        if(confirmado == JOptionPane.YES_OPTION){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        }else{
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    /**
     * action to open a game state
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void actionOpen() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            QuoriPOOB quoriPOOB = QuoriPOOB.open(archivo); 
            if (quoriPOOB != null) {
                QuoriPOOB.resetInstance(); 
                QuoriPOOB.setInstanciaUnica(quoriPOOB);
                repintar();
                paintFichas(); 
                paintWall(); 
            } else {
                System.out.println("Error al cargar el archivo.");
            }
        }
    }



    /**
     * action to save the state of one game
     */
    private void actionSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        int sel = fileChooser.showSaveDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String filePath = archivo.getAbsolutePath();
            QuoriPOOB.getInstance().save(archivo);
			JOptionPane.showMessageDialog(null, "Se ha guardado exitosamente en: " + filePath);
        }
    }
    
    /**
     * action to start a new game
     * @throws QuoriPOOBException
     */
    private void actionNew() throws QuoriPOOBException {
    	modoSeleccionado = (String) modoDeJuego.getSelectedItem();
        tipoSeleccionado = (String) tipoDeJuego.getSelectedItem();
        nivelSeleccionado = (String) nivelDeDificultad.getSelectedItem();
        if (tipoSeleccionado.equals("Contrarreloj") || tipoSeleccionado.equals("Cronometrado")) {
        	ingresarDatosContrarreloj();
        }else {
        	ingresarDatos();
        }	
        remove(configurationScreen);
        repaint();
        revalidate();
    }
    
    /**
     * action to appear the configuration screen
     */
    private void actionConfiguration() {
    	prepareConfigurationScreen();
    	remove(initialScreen);
    	repaint();
    	revalidate();
    }
    
    /**
     * action to appear the initial screen
     */
    public void actionInitialScreen() {
    	remove(main);
    	prepareInitialScreen();
    	prepareElementsMenu();
    	prepareActions();
    	repaint();
    	revalidate();
    }
    
    
    /**
     * actions for configuration screen
     */
    private void prepareActionsConfiguration() {
    	inicioC.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		try {
					actionNew();
				} catch (QuoriPOOBException e) {
					e.printStackTrace();
				}
        	}
        });
    	
    	 modoDeJuego.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if ("Jugador vs Maquina".equals(modoDeJuego.getSelectedItem())) {
                     nivelDeDificultad.setVisible(true);
                     textoSeleccionarNivelDeDificultad.setVisible(true);
                 } else {
                     nivelDeDificultad.setVisible(false);
                     textoSeleccionarNivelDeDificultad.setVisible(false);
                 }
                 configurationScreen.revalidate();
                 configurationScreen.repaint();
             }
         });
    }
    
    
    /**
     * prepare actions for the board (cells and walls)
     */
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




    /**
     * elements of panel for the player 1
     * @param mainPanel
     * @param playerName1
     * @param player1Color
     */
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
        datosJugador1Panel.setLayout(new GridLayout(7, 1));
        jugador1Turno = new JLabel("",SwingConstants.CENTER);
        datosJugador1Panel.add(jugador1Turno);
        paredesLabel1 = new JLabel("Paredes: " + QuoriPOOB.getInstance().getPlayer1().getNWalls(), SwingConstants.CENTER);
        normales1 = new JLabel("normales: " + QuoriPOOB.getInstance().getPlayer1().getNormales(), SwingConstants.CENTER);
        aliadas1 = new JLabel("aliadas: " + QuoriPOOB.getInstance().getPlayer1().getAliadas(), SwingConstants.CENTER);
        temporales1 = new JLabel("temporales: " + QuoriPOOB.getInstance().getPlayer1().getTemporales(), SwingConstants.CENTER);
        largas1 = new JLabel("largas: " + QuoriPOOB.getInstance().getPlayer1().getLargas(), SwingConstants.CENTER);
        datosJugador1Panel.add(paredesLabel1);
        datosJugador1Panel.add(normales1);
        datosJugador1Panel.add(aliadas1);
        datosJugador1Panel.add(temporales1);
        datosJugador1Panel.add(largas1);
        if ("Contrarreloj".equals(tipoDeJuego.getSelectedItem()) || "Cronometrado".equals(tipoDeJuego.getSelectedItem())) {
        	String tipoSeleccionado = (String) tipoDeJuego.getSelectedItem();
            contadorJugador1 = new Contador(tiempoJuegoContrarreloj,tipoSeleccionado,this);  // Donde tiempoDeJuego es el tiempo inicial para el juego contrarreloj
            datosJugador1Panel.add(contadorJugador1); // O jugador2Panel, dependiendo de dónde lo quieras mostrar
        }
        jugador1Panel.add(datosJugador1Panel,BorderLayout.CENTER);
        mainPanel.add(jugador1Panel, BorderLayout.WEST);
    }


    /**
     * elements of panel for the player 2
     * @param mainPanel
     * @param playerName2
     * @param player2Color
     */
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
        datosJugador2Panel.setLayout(new GridLayout(7, 1));
        jugador2Turno = new JLabel("",SwingConstants.CENTER);
        datosJugador2Panel.add(jugador2Turno);
        paredesLabel2 = new JLabel("Paredes: " + QuoriPOOB.getInstance().getPlayer2().getNWalls(), SwingConstants.CENTER);
        normales2 = new JLabel("normales: " + QuoriPOOB.getInstance().getPlayer2().getNormales(), SwingConstants.CENTER);
        aliadas2 = new JLabel("aliadas: " + QuoriPOOB.getInstance().getPlayer2().getAliadas(), SwingConstants.CENTER);
        temporales2 = new JLabel("temporales: " + QuoriPOOB.getInstance().getPlayer2().getTemporales(), SwingConstants.CENTER);
        largas2 = new JLabel("largas: " + QuoriPOOB.getInstance().getPlayer2().getLargas(), SwingConstants.CENTER);
        datosJugador2Panel.add(paredesLabel2);
        datosJugador2Panel.add(normales2);
        datosJugador2Panel.add(aliadas2);
        datosJugador2Panel.add(temporales2);
        datosJugador2Panel.add(largas2);
        if ("Contrarreloj".equals(tipoDeJuego.getSelectedItem()) || "Cronometrado".equals(tipoDeJuego.getSelectedItem())) {
        	String tipoSeleccionado = (String) tipoDeJuego.getSelectedItem();
            contadorJugador2 = new Contador(tiempoJuegoContrarreloj,tipoSeleccionado,this); // Donde tiempoDeJuego es el tiempo inicial para el juego contrarreloj
            datosJugador2Panel.add(contadorJugador2); // O jugador2Panel, dependiendo de dónde lo quieras mostrar
        }
        jugador2Panel.add(datosJugador2Panel,BorderLayout.CENTER);
        mainPanel.add(jugador2Panel, BorderLayout.EAST);
    }


    /**
     * do the board in a Jpanel
     * @param mainPanel
     */
    private void tableroPanel(JPanel mainPanel){
    	this.size = QuoriPOOB.getInstance().getSize();
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
                }else if(tablero[i][j] == 0){
                	casilla.setBackground(Color.white);
                }else if(tablero[i][j] == 5) {
                	Color paleGreen = new Color(152, 251, 152);
                	casilla.setBackground(paleGreen);
                }else if(tablero[i][j] == 7) {
                	Color rojoPalido = new Color(255, 192, 192);
                	casilla.setBackground(rojoPalido);
                }else if(tablero[i][j] == 6) {
                	Color moradoPalido = new Color(219, 112, 219);
                	casilla.setBackground(moradoPalido);
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
    
    /**
     * repaint the board
     */
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
    
    /**
     * paint tokens
     */
    private void paintFichas() {
    	for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
            	if(tablero[i][j] == 0) {
                	casillas[i][j].setBackground(Color.white);
            	}
            }
        }
        Token t1 = QuoriPOOB.getInstance().getT1();
        Token t2 = QuoriPOOB.getInstance().getT2();
        Color c1 = t1.getColor();
        Color c2 = t2.getColor();
        casillas[t1.getFila()][t1.getColumna()].setBackground(c1);
        casillas[t2.getFila()][t2.getColumna()].setBackground(c2);
        
        paintWall();
        revalidate();
        repaint();
    }
    

    /**
     * actions for the buttons in the cell position
     * @param button
     * @throws QuoriPOOBException
     */
    private void actionCell(JButton button) throws QuoriPOOBException {
        int fila = (int) button.getClientProperty("fila");
        int columna = (int) button.getClientProperty("columna");
        boolean moveSuccessful = QuoriPOOB.getInstance().move(fila, columna);
        if (moveSuccessful) {
            paintFichas();
            boolean verify = QuoriPOOB.getInstance().verificarVictoria();
            if (verify) {
                int confirmado = JOptionPane.showConfirmDialog(this, "El jugador " + QuoriPOOB.getInstance().getCurrentPlayer().getName() + " ha ganado el juego. ¿Quieres salir del juego?", "Victoria", JOptionPane.YES_NO_OPTION);
                if (confirmado == JOptionPane.YES_OPTION) {
                    actionClose();
                } else {
                    actionInitialScreen();
                }
            } else {
            	paintFichas();
            	QuoriPOOB.getInstance().cambiaTurno();
            	QuoriPOOB.getInstance().activarAct();
                actualizarTurno();
                if(modoSeleccionado.equals("Jugador vs Maquina")) {
            		principianteMachine p =(principianteMachine)QuoriPOOB.getInstance().getPlayer2();
            		p.makeAction();
            		paintFichas();
            		paintWall();
            		QuoriPOOB.getInstance().cambiaTurno();
            	}
            }
        }
    }
    
    /**
     * actions for the buttons in the wall positions
     * @param button
     */
    private void actionWall(JButton button) {
    	int fila = (int)button.getClientProperty("fila");
        int columna = (int)button.getClientProperty("columna");
        if(fila % 2 != 0 && columna % 2 != 0) {
        	JOptionPane.showMessageDialog(null,"En este sitio no se puede poner una pared");
        }else if(fila % 2 != 0) {
        	String tipo = selectTypeOfWall();
        	QuoriPOOB.getInstance().putWall(true, fila, columna, tipo);
        }else if(columna % 2 != 0) {
        	String tipo = selectTypeOfWall();
        	QuoriPOOB.getInstance().putWall(false, fila, columna, tipo);
        }
        paintWall();
        QuoriPOOB.getInstance().cambiaTurno();
        refresh();   
        actualizarTurno();
        if(modoSeleccionado.equals("Jugador vs Maquina")) {
        	principianteMachine p =(principianteMachine)QuoriPOOB.getInstance().getPlayer2();
    		p.makeAction();
    		paintFichas();
    		paintWall();
    		QuoriPOOB.getInstance().cambiaTurno();
    		refresh(); 
    	}
    }
    
    /**
     * Select the type of walls
     * @return
     */
    private String selectTypeOfWall() {
    	
		String[] options = {"NormalWall", "TemporalWall", "AllyWall", "LargeWall"};
	    String tipo = (String) JOptionPane.showInputDialog(
	    null,
	    "Seleccione el tipo de barrera que desea colocar:",
	    "Barreras",
	    JOptionPane.PLAIN_MESSAGE,
	    null,
	    options,
	    options[0]);
	    return tipo;     
	}
    
    
    /**
     * reset the game
     * @throws QuoriPOOBException
     */
    private void resetGame() throws QuoriPOOBException {
    	
    	QuoriPOOB.getInstance().reset();
    	repintar();
        paintFichas();
        refresh();
    }
    
    /**
     * paint walls in the board
     */
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
    			}else if(tablero[i][j] == 2) {
    				casillas[i][j].setBackground(Color.LIGHT_GRAY);
    			}else if(tablero[i][j] == 4) {
    				casillas[i][j].setBackground(Color.black);
    			}else if(tablero[i][j] == 10) {
    				
    				casillas[i][j].setBackground(colorWallAlly(i,j));
    			}
    		}
    	}
    }
    
    
    /**
     * get the color for the ally walls
     * @param i
     * @param j
     * @return
     */
    private Color colorWallAlly(int i, int j) {
    	Player p = QuoriPOOB.getInstance().getCurrentPlayer();
    	Color c = null;
    	if(p == QuoriPOOB.getInstance().getPlayer1()) {
    		c  = QuoriPOOB.getInstance().getPlayer1().getToken().getColor();
    	}else if(p == QuoriPOOB.getInstance().getPlayer2()) {
    		c  = QuoriPOOB.getInstance().getPlayer2().getToken().getColor();
    	}
    	return c;
    	
    }
    
    /**
     * refresh the panels for the players
     */
    private void refresh() {
    	 paredesLabel1.setText("Paredes: " + QuoriPOOB.getInstance().getPlayer1().getNWalls());
    	 normales1.setText("normales: " + QuoriPOOB.getInstance().getPlayer1().getNormales());
    	 aliadas1.setText("aliadas: " + QuoriPOOB.getInstance().getPlayer1().getAliadas());
    	 temporales1.setText("temporales: " + QuoriPOOB.getInstance().getPlayer1().getTemporales());
    	 largas1.setText("largas: " + QuoriPOOB.getInstance().getPlayer1().getLargas());
    	 
    	 paredesLabel2.setText("Paredes: " + QuoriPOOB.getInstance().getPlayer2().getNWalls());
    	 normales2.setText("normales: " + QuoriPOOB.getInstance().getPlayer2().getNormales());
    	 aliadas2.setText("aliadas: " + QuoriPOOB.getInstance().getPlayer2().getAliadas());
    	 temporales2.setText("temporales: " + QuoriPOOB.getInstance().getPlayer2().getTemporales());
    	 largas2.setText("largas: " + QuoriPOOB.getInstance().getPlayer2().getLargas());
    	 
    }
    
    /**
     * Update the turn player in the presentation
     */
    private void actualizarTurno() {
    	String tipoSeleccionado = (String) tipoDeJuego.getSelectedItem();
        if (QuoriPOOB.getInstance().getCurrentPlayer() == QuoriPOOB.getInstance().getPlayer1()) {
            jugador1Turno.setText("Es tu turno");
            jugador2Turno.setText("");
            if (tipoSeleccionado.equals("Contrarreloj")) {
            	contadorJugador1.reiniciarContador();
                contadorJugador1.iniciarContador();
                contadorJugador2.detenerContador();
            }	
            if (tipoSeleccionado.equals("Cronometrado")) {
            	contadorJugador1.iniciarContador();
                contadorJugador2.detenerContador();
            }
        } else if (QuoriPOOB.getInstance().getCurrentPlayer() == QuoriPOOB.getInstance().getPlayer2()) {
            jugador2Turno.setText("Es tu turno");
            jugador1Turno.setText("");
            if (tipoSeleccionado.equals("Contrarreloj")) {
            	contadorJugador2.reiniciarContador();
                contadorJugador2.iniciarContador();
                contadorJugador1.detenerContador();
            }	
            if (tipoSeleccionado.equals("Cronometrado")) {
            	contadorJugador2.iniciarContador();
                contadorJugador1.detenerContador();
            }
        }
    }

    /**
     * enter data in the normal mode
     * @throws QuoriPOOBException
     */
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
    	if(modoSeleccionado.equals("Jugador vs Maquina")) {
    		QuoriPOOB.getInstance(player1Color,player2Color,size,temporales,largas,aliadas
        			,cTeletransportador,cRegresar,cTurnoDoble,true);
    	}else {
    		QuoriPOOB.getInstance(player1Color,player2Color,size,temporales,largas,aliadas
        			,cTeletransportador,cRegresar,cTurnoDoble,false);
    	}
    	tablero = QuoriPOOB.getInstance().getTablero();
        prepareElementsBoard(playerName1,playerName2,player1Color,player2Color);
    }
    
    /**
     * enter data in the contrarreloj and cronometado modes
     * @throws QuoriPOOBException
     */
    private void ingresarDatosContrarreloj() throws QuoriPOOBException {
    	String playerName1 = JOptionPane.showInputDialog(this, "Ingrese el nombre del jugador 1:");
    	String playerName2 = JOptionPane.showInputDialog(this, "Ingrese el nombre del jugador 2:");
    	Color player1Color = JColorChooser.showDialog(this, "Seleccione el color de la ficha para " + playerName1, Color.RED);
    	Color player2Color = JColorChooser.showDialog(this, "Seleccione el color de la ficha para " + playerName2, Color.BLUE);
    	String size = JOptionPane.showInputDialog(this, "Ingrese el tamaño del tablero: ");
    	String time = JOptionPane.showInputDialog("Ingrese el tiempo de cada turno para los jugadores: ");
    	convTime(time);
    	String temporales = JOptionPane.showInputDialog(this, "Ingrese el numero de paredes temporales: ");
    	String largas = JOptionPane.showInputDialog(this, "Ingrese el numero de paredes largas: ");
    	String aliadas = JOptionPane.showInputDialog(this, "Ingrese el numero de paredes aliadas: ");
    	String cTeletransportador = JOptionPane.showInputDialog(this, "Ingrese el numero de casillas teletransportadoras: ");
    	String cRegresar = JOptionPane.showInputDialog(this, "Ingrese el numero de casillas regresadoras: ");
    	String cTurnoDoble = JOptionPane.showInputDialog(this, "Ingrese el numero de casillas de turno doble: ");
    	
    	QuoriPOOB.getInstance(player1Color,player2Color,size,temporales,largas,aliadas
    			,cTeletransportador,cRegresar,cTurnoDoble,false);
    	tablero = QuoriPOOB.getInstance().getTablero();
        prepareElementsBoard(playerName1,playerName2,player1Color,player2Color);
    }
    
    
    private void convTime(String time) {
    	try {
			this.tiempoJuegoContrarreloj = Integer.parseInt(time);
		}catch(NumberFormatException e) {
			String n =JOptionPane.showInputDialog(this, "Reingrese el tiempo de juego");
			convTime(n);
		}
	}
    

    /**
     * main
     * @param args
     */
    public static void main(String[] args){
        JFrame frame = new QuoriPOOBGUI();
        frame.setVisible(true);
    }
    
}