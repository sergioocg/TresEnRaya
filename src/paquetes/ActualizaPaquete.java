package paquetes;

import java.io.Serializable;

public class ActualizaPaquete implements Serializable {
    private static final long serialVersionUID = 4514328168496009986L;

    private int[][] casillas;
    private int jugadorActual;

    public ActualizaPaquete(int[][] casillas, int jugadorActual) {
        this.casillas = casillas;
        this.jugadorActual = jugadorActual;
    }

    public int[][] getCasillas() {
        return casillas;
    }

    public int getJugadorActual() { return jugadorActual; }
}
