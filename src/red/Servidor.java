package red;

import juego.Juego;
import paquetes.PaqueteCliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Juego {
    public Servidor() {
        super(JUGADOR1);

        try {
            serverSocket = new ServerSocket(Juego.PUERTO);
            Socket socket = serverSocket.accept();
            conexion = new Conexion(this, socket);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void movimiento(int x, int y) {
        if(esMiTurno()) { actualizaCasilla(x, y); }
        contenidoVentana.repaint();
    }

    @Override
    public void paqueteRecibido(Object object) {
        if(object instanceof PaqueteCliente) {
            PaqueteCliente pq = (PaqueteCliente) object;

            actualizaCasilla(pq.getX(), pq.getY());
        }
        contenidoVentana.repaint();
    }

    @Override
    public void cerrarJuego() {
        try {
            conexion.cerrarConexion();
            serverSocket.close();

            System.exit(0);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
