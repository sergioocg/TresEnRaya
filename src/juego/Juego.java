package juego;

import gui.ContenidoVentana;
import gui.Ventana;
import paquetes.ActualizaPaquete;
import red.Conexion;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Juego {
    public static final int PUERTO = 60000;
    public final int ANCHO = 600, ALTO = 600;
    public final int ANCHO_CELDA = ALTO / 3;
    public final int ALTO_CELDA = ALTO / 3;
    public static final int LIBRE = 0, JUGADOR1 = 1, JUGADOR2 = 2;

    protected ContenidoVentana contenidoVentana;

    protected ServerSocket serverSocket;
    protected Conexion conexion;
    protected Socket socket;

    public int[][] casillas;
    protected int jugadorActual, esteJugador;


    public Juego(int esteJugador) {
        this.esteJugador = esteJugador;

        Ventana ventana = new Ventana(this, "Tres en rayas" + " | " + queJugador(esteJugador), ANCHO, ALTO);
        contenidoVentana = new ContenidoVentana(this);

        ventana.add(contenidoVentana);
        ventana.setVisible(true);

        casillas = new int[3][3];
        jugadorActual = esteJugador;
    }


    public abstract void movimiento(int x, int y);

    public abstract void paqueteRecibido(Object object);

    public abstract void cerrarJuego();

    public String queJugador(int esteJugador) {
        if (esteJugador == JUGADOR1) return "Servidor (JUGADOR 1)";
        else return "Cliente (JUGADOR 2)";
    }

    protected boolean esMiTurno() {
        return esteJugador == jugadorActual;
    }

    public int[][] getCasillas() {
        return casillas;
    }

    protected void actualizaCasilla(int x, int y) {
        if(casillas[x][y] == LIBRE) {
            casillas[x][y] = jugadorActual;

            if(jugadorActual == JUGADOR1) {
                jugadorActual = JUGADOR2;
            }
            else {
                if(jugadorActual == JUGADOR2) {
                    jugadorActual = Juego.JUGADOR1;
                }
            }
            conexion.enviarPaquete(new ActualizaPaquete(casillas, jugadorActual));

            int jugador = comprobarGanador();
            if(comprobarGanador() != -1) {
                finalizaPartida(jugador);
            }
            else {
                int contVacio = 0;

                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        if(casillas[i][j] == LIBRE) {
                            contVacio++;
                        }
                    }
                }
                if(contVacio == 0) {
                    finalizaPartida(contVacio);
                }
            }
        }
    }

    private int comprobarGanador() {
        int contJugador;
        for(int jugador = JUGADOR1; jugador <= JUGADOR2; jugador++) {
            for(int y = 0; y < 3; y++) { // Comprobaciones verticales
                contJugador = 0;

                for(int x = 0; x < 3; x++) {
                    if(casillas[x][y] == jugador) { contJugador++; }
                }
                if(contJugador == 3) { return jugador; }
            }

            for(int x = 0; x < 3; x++) { // Comprobaciones horizontales
                contJugador = 0;

                for(int y = 0; y < 3; y++) {
                    if(casillas[x][y] == jugador) { contJugador++; }
                }
                if(contJugador == 3) { return jugador; }
            }

            // Diagonal izquierda a derecha
            contJugador = 0;
            for(int coordinate = 0; coordinate < 3; coordinate++) {
                if(casillas[coordinate][coordinate] == jugador) { contJugador++; }
            }
            if(contJugador == 3) { return jugador; }

            // Diagonal derecha a izquierda
            contJugador = 0;
            for(int coordinate = 0; coordinate < 3; coordinate++) {
                if(casillas[2 - coordinate][coordinate] == jugador) { contJugador++; }
            }
            if(contJugador == 3) { return jugador; }
        }
        return -1;
    }

    protected void mostrarGanador(int usuarioGanador) {
        switch(usuarioGanador) {
            case JUGADOR1:
                JOptionPane.showMessageDialog(null, "¡No conoces el poder del lado oscuro!", "Darth Vader", JOptionPane.INFORMATION_MESSAGE);
            break;

            case JUGADOR2:
                JOptionPane.showMessageDialog(null, "¡La fuerza está con nosotros!", "Luke Skywalker", JOptionPane.INFORMATION_MESSAGE);
            break;

            default:
                JOptionPane.showMessageDialog(null, "Difícil de ver el futuro es", "Maestro Yoda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void finalizaPartida(int usuarioGanador) {
        mostrarGanador(usuarioGanador);

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                casillas[x][y] = LIBRE;
            }
        }
        contenidoVentana.repaint();
    }
}

