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
    private QuoriPOOB game;
    
    public Contador(int tiempoInicial, QuoriPOOB game) {
        this.tiempoInicial = tiempoInicial;
        this.game = game;
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
            JOptionPane.showMessageDialog(null, "Se te acabo el tiempo perdiste tu tiempo");
            game.cambiaTurno();// Detiene el temporizador cuando el tiempo llega a cero
            // Aquí puedes agregar cualquier acción que desees realizar cuando se acabe el tiempo
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