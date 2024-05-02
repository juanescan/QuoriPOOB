package presentation;

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
    private Color gris = new Color(238,238,238);

    private JPanel main;

    public QuoriPOOBGUI(){

        prepareElements();
        prepareElementsBoard();
        prepareElementsMenu();
        prepareActions();


    }



    private void prepareElements(){

        setTitle("QuoriPOOBGUI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Nueva posicion de la pantalla
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
        boardPanel.setLayout(new GridLayout(9,9));
        casillas = new JButton[9][9];
        int casillaSize = 50;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                JButton casilla = new JButton();
                casilla.setPreferredSize(new Dimension(casillaSize, casillaSize));
                casillas[i][j] = casilla;
                boardPanel.add(casilla);
            }
        }
        mainPanel.add(boardPanel, BorderLayout.CENTER);
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





    public static void main(String[] args){
        JFrame frame = new QuoriPOOBGUI();
        frame.setVisible(true);
    }
    
}