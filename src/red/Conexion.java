package red;

import juego.Juego;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Conexion implements Runnable {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Juego juego;

    public Conexion(Juego juego, Socket socket) {
        this.juego = juego;

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void enviarPaquete(Object object) {
        try {
            outputStream.reset();

            outputStream.writeObject(object);
            outputStream.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void cerrarConexion() {
        try {
            inputStream.close();
            outputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean running = true;

        while(running) {
            try {
                Object o = inputStream.readObject();
                juego.paqueteRecibido(o);

            }catch(EOFException | SocketException e) {
                running = false;

            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
