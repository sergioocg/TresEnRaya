package paquetes;

import java.io.Serializable;

public class PaqueteCerrar implements Serializable {
    private static final long serialVersionUID = 3857084431533244765L;

    private int winner;

    public PaqueteCerrar(int winner) {
        this.winner = winner;
    }

    public int getWinner() { return winner; }
}
