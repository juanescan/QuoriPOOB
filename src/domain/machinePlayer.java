package domain;



import java.awt.Color;

public class machinePlayer extends Player {
    public machinePlayer(String name,Token token, int nWalls, int normales, int largas, int temporales, int aliadas) {
        super(name, token, nWalls, normales, largas,temporales,aliadas);
    }

    public void makeMove(QuoriPOOB game) {
        // Implementa la lógica del movimiento automático aquí.
        // Este método será llamado para realizar un movimiento automáticamente.
    }
}

