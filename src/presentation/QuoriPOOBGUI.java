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
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/*
 * Represents the QuoriPOOBGUI game
 * version 1.0
 * Santiago Córdoba
 */
public class QuoriPOOBGUI extends JFrame{


    private JMenuBar barra;
    private JMenu menu;
    private JMenuItem abrir;
    private JMenuItem nuevo;
    private JMenuItem salir;
    private JMenuItem salvar;
    private JFileChooser fileChooser;
    private JButton[][] casillas;
    private JTextField movs;
    private JTextField sucessToken;
    private JPanel initialScreen;
    private JButton inicio;
    private JButton salirB;
    private JPanel main;
    private QuoriPOOB game;
    private int[][] tablero;

    public QuoriPOOBGUI(){

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
        abrir= new JMenuItem("abrir");
        nuevo = new JMenuItem("nuevo");
        salvar = new JMenuItem("salvar");
        salir = new JMenuItem("salir");

        setJMenuBar(barra);
        barra.add(menu);
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(salvar);
        menu.add(salir);
    }

    private void prepareElementsBoard(){
        main = new JPanel();
        main.setLayout(new BorderLayout());
        //llama al metodo que hace la parte de los datos
        jugador1(main);
        // llama al metodo que hace el tablero de juego
        tableroPanel(main);
        //llama al metodo para los botones de reiniciar y configuracion y cambiar tamano
        jugador2(main);
        //llama al metodo para los botones del los movimientos del tablero
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
                actionOpen();
            }
        });

        salvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                actionSave();
            }
        });


        inicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		actionNew();
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
            dispose();
        }else{
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    private void actionOpen() {
        fileChooser.setVisible(true);
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "the file " + fichero.getName() + " can not be opened because the functionalities are under construction");
        }
    }

    private void actionSave(){
        fileChooser.setVisible(true);
        int selection = fileChooser.showSaveDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "the file " + fichero.getName() + " can not be saved because the functionalities are under construction");
        }
    }
    
    private void actionNew() {
    	game = new QuoriPOOB();
        tablero = game.getTablero();
        prepareElementsBoard();
        remove(initialScreen);
        revalidate();
        repaint();
    }
    
    private void prepareActionsBoard(){
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 17; j++){
                final JButton currentButton = casillas[i][j];
                final int x = i; 
                final int y = j; 
                currentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev){
                        if(x % 2 != 0 || y % 2 != 0) {
                            actionWall(currentButton);
                        }
                        else {
                            actionCell(currentButton);
                        }
                    }
                });
            }
        }
    }







    private void jugador1(JPanel mainPanel) {
        // Crear un panel para el jugador 1
        JPanel jugador1Panel = new JPanel();
        jugador1Panel.setLayout(new BorderLayout());

        // Crear un título para el panel del jugador 1
        JLabel jugador1Label = new JLabel("Jugador 2", SwingConstants.CENTER);
        jugador1Label.setFont(new Font("Arial", Font.BOLD, 16)); // Ajustar la fuente y el tamaño
        jugador1Panel.add(jugador1Label, BorderLayout.NORTH);

        // Panel para los datos del jugador 1 (paredes y movimientos)
        JPanel datosJugador1Panel = new JPanel();
        datosJugador1Panel.setLayout(new GridLayout(2, 1));

        // Agregar JLabels para "paredes" y "movimientos hechos"
        JLabel paredesLabel = new JLabel("Paredes: ", SwingConstants.CENTER);
        JLabel movimientosHechosLabel = new JLabel("Movimientos hechos: ", SwingConstants.CENTER);

        // Agregar los JLabels al panel de datos del jugador 1
        datosJugador1Panel.add(paredesLabel);
        datosJugador1Panel.add(movimientosHechosLabel);

        // Agregar el panel de datos del jugador 1 al panel del jugador 1
        jugador1Panel.add(datosJugador1Panel, BorderLayout.CENTER);

        // Agregar el panel del jugador 1 al panel principal
        mainPanel.add(jugador1Panel, BorderLayout.EAST);
    }

    private void tableroPanel(JPanel mainPanel){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(17,17));
        casillas = new JButton[17][17];
        int casillaSize = 20;
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 17; j++){
                JButton casilla = new JButton();
                if(i % 2 != 0 || j % 2 != 0) {
                	casilla.setBackground(Color.lightGray);
                }else {
                	casilla.setBackground(Color.white);
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
    
    private void paintFichas() {
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 17; j++){
                if (tablero[i][j] == 1){
                    Token t = game.getToken(i,j);
                    Color c = t.getColor();
                    casillas[i][j].setBackground(c); 
                }else if(tablero[i][j] == 0) {
                	casillas[i][j].setBackground(Color.white);
                }
                	
            }
        }
        revalidate();
        repaint();
    }

    private void jugador2(JPanel mainPanel) {
        // Crear un panel para el jugador 1
        JPanel jugador1Panel = new JPanel();
        jugador1Panel.setLayout(new BorderLayout());

        // Crear un título para el panel del jugador 1
        JLabel jugador1Label = new JLabel("Jugador 1", SwingConstants.CENTER);
        jugador1Label.setFont(new Font("Arial", Font.BOLD, 16)); // Ajustar la fuente y el tamaño
        jugador1Panel.add(jugador1Label, BorderLayout.NORTH);

        // Panel para los datos del jugador 1 (paredes y movimientos)
        JPanel datosJugador1Panel = new JPanel();
        datosJugador1Panel.setLayout(new GridLayout(2, 1));

        // Agregar JLabels para "paredes" y "movimientos hechos"
        JLabel paredesLabel = new JLabel("Paredes: ", SwingConstants.CENTER);
        JLabel movimientosHechosLabel = new JLabel("Movimientos hechos: ", SwingConstants.CENTER);

        // Agregar los JLabels al panel de datos del jugador 1
        datosJugador1Panel.add(paredesLabel);
        datosJugador1Panel.add(movimientosHechosLabel);

        // Agregar el panel de datos del jugador 1 al panel del jugador 1
        jugador1Panel.add(datosJugador1Panel, BorderLayout.CENTER);

        // Agregar el panel del jugador 1 al panel principal
        mainPanel.add(jugador1Panel, BorderLayout.WEST);
    }

    private void actionCell(JButton button) {
        int fila = (int)button.getClientProperty("fila");
        int columna = (int)button.getClientProperty("columna");
        game.move(fila, columna); 
        paintFichas();
    }
    
    private void actionWall(JButton button) {
    	
    }
    
    
    

    public static void main(String[] args){
        JFrame frame = new QuoriPOOBGUI();
        frame.setVisible(true);
    }
    
}