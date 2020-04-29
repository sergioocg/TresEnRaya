package red;

import juego.Juego;
import paquetes.ActualizaPaquete;
import paquetes.PaqueteCerrar;
import paquetes.PaqueteCliente;

import java.io.IOException;
import java.net.Socket;

public class Cliente extends Juego {
    public Cliente() {
        super(JUGADOR2);

        try {
            socket = new Socket("localhost", PUERTO);
            conexion = new Conexion(this, socket);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void movimiento(int x, int y) {
        if(esMiTurno()) { conexion.enviarPaquete(new PaqueteCliente(x, y)); }
        contenidoVentana.repaint();
    }

    @Override
    public void paqueteRecibido(Object object) {
        if(object instanceof ActualizaPaquete) {
            ActualizaPaquete pq = (ActualizaPaquete) object;

            casillas = pq.getCasillas();
            jugadorActual = pq.getJugadorActual();
        }
        else {
            if(object instanceof PaqueteCerrar) {
                PaqueteCerrar pq = (PaqueteCerrar)object;

                mostrarGanador(pq.getWinner());
            }
        }
        contenidoVentana.repaint();
    }

    @Override
    public void cerrarJuego() {
        try {
            conexion.cerrarConexion();
            socket.close();

            System.exit(0);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
