package gui;

import juego.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana extends JFrame {
    private Juego juego;

    public Ventana(Juego juego, String titulo, int ancho, int altura) {
        super(titulo);
        this.juego = juego;

        setSize(ancho, altura);
        setLocationRelativeTo(null); // Centramos la ventana en la pantalla
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setPreferredSize(new Dimension(ancho, altura));
        pack();

        addWindowListener(new Listener());
    }

    class Listener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            juego.cerrarJuego();
        }
    }
}
