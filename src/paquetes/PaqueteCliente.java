package paquetes;

import java.io.Serializable;

public class PaqueteCliente implements Serializable {
    private static final long serialVersionUID = -8286226668267663612L;

    private int x, y;

    public PaqueteCliente(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
