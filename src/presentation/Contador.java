package presentation;


import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contador extends JPanel {
    private JLabel labelTiempo;
    private int tiempoRestante;
    private Timer timer;
    private int tiempoInicial;
    private String tipoJuego;
    private QuoriPOOBGUI pantalla;

    
    public Contador(int tiempoInicial,String tipoJuego,QuoriPOOBGUI pantalla) {
        this.tiempoInicial = tiempoInicial;
        this.tipoJuego = tipoJuego;
        this.pantalla = pantalla;
        
        reiniciarContador(); // Método para iniciar el contador con el tiempo inicial
    }

    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();
            }
        });
        timer.start();
    }
    
    public void iniciarContador() {
        if (!timer.isRunning()) { // Verifica si el temporizador no está en funcionamiento para evitar iniciar múltiples temporizadores
            timer.start();
        }
    }
    
    public void detenerContador() {
        timer.stop(); // Detiene el temporizador
    }

    private void detenerTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public void reiniciarContador() {
        tiempoRestante = tiempoInicial;
        if (timer != null) {
            detenerTimer();
        }
        iniciarTimer();
        actualizarLabel();
    }


    private void actualizarTiempo() {
        tiempoRestante--;
        if (tiempoRestante <= 0) {
            tiempoRestante = 0;
            detenerTimer();
            if (tipoJuego.equals("Contrarreloj")) {
                JOptionPane.showMessageDialog(null, "Se te acabó el tiempo, perdiste tu turno.");
            } else if (tipoJuego.equals("Cronometrado")) {
                JOptionPane.showMessageDialog(null, "Se le acabó el tiempo al jugador: " + QuoriPOOB.getInstance().getCurrentPlayer().getName());
                if(QuoriPOOB.getInstance().getCurrentPlayer() == QuoriPOOB.getInstance().getPlayer1()) {
                	int confirmado = JOptionPane.showConfirmDialog(this, "El jugador " + QuoriPOOB.getInstance().getPlayer2().getName()
                			+ " ha ganado el juego. ¿Quieres salir del juego?", "Victoria", JOptionPane.YES_NO_OPTION);
                	if (confirmado == JOptionPane.YES_OPTION) {
                        pantalla.actionClose();
                    } else {
                        pantalla.actionInitialScreen();
                    }
                }else if(QuoriPOOB.getInstance().getCurrentPlayer() == QuoriPOOB.getInstance().getPlayer2()) {
                	int confirmado = JOptionPane.showConfirmDialog(this, "El jugador " + QuoriPOOB.getInstance().getPlayer1().getName()
                			+ " ha ganado el juego. ¿Quieres salir del juego?", "Victoria", JOptionPane.YES_NO_OPTION);
                	if (confirmado == JOptionPane.YES_OPTION) {
                        pantalla.actionClose();
                    } else {
                        pantalla.actionInitialScreen();
                    }
                }
                
                
            }

            QuoriPOOB.getInstance().cambiaTurno();
        }
        actualizarLabel();
    }
    
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    private void actualizarLabel() {
        labelTiempo = new JLabel(tiempoRestante + " segundos");
        removeAll(); // Elimina cualquier componente anterior
        add(labelTiempo);
        revalidate(); // Vuelve a validar la interfaz para mostrar los cambios
        repaint(); // Vuelve a pintar la interfaz para mostrar los cambios
    }
}