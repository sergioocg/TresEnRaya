// Sergio Ocaña García

package juego;

import red.Cliente;
import red.Servidor;

import javax.swing.*;

/**
 * Cosas a tener en cuenta
 *  - 1. Compilar con SDK 14.
 *  - 2. Es necesario abrir primero el Servidor y después el Cliente, sinó, dará error de conexión.
 *  - 3. No se pueden abrir dos Servidores al mismo tiempo.
 *  - 4. Al principio, el 1er turno siempre será del Servidor (Jugador 1), hay un error que deja al Cliente (Jugador 2)
 *  poner dos fichas, la del Jugador 1 y la suya propia.
 *  - 5. Cuando finaliza una partida, el tablero se limpia (pero en uno de los dos ejecutables aún se quedan las fichas
 *  porque no ha recibido aún el paquete), y se reinician las posiciones pudiendo volver a jugar.
 */

public class Ejecutar {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "1. Uno de los jugadores debe ser el Servidor," +
                " y el otro el Cliente (de otra forma dará error de conexión)" +
                "\n2. El 1er turno será siempre del Servidor (Jugador 1)" +
                "\n*****hay un error y en el 1er turno el Cliente (Jugador 2) puede poner dos fichas, la del Jugador 1 y la suya*****", "Instrucciones", JOptionPane.INFORMATION_MESSAGE);

        String[] opciones = {"Servidor", "Cliente"};
        String opcion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción: ",
                "Opción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if(opcion.contains(opciones[0])) {
            new Servidor();
        }
        else {
            new Cliente();
        }
    }
}
