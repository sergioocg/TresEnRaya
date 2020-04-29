package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import juego.Juego;
import juego.Resources;

public class ContenidoVentana extends JPanel {
    private Juego juego;

    public ContenidoVentana(Juego juego) {
        this.juego = juego;
        addMouseListener(new Input());
    }

    public void dibujarContenido(Graphics2D g2D) {
        /**
         * Líneas verticales
         */
        for(int y = juego.ANCHO_CELDA; y <= juego.ANCHO_CELDA * 2; y += juego.ANCHO_CELDA) {
            g2D.drawLine(y, 0, y, juego.ALTO);
        }

        /**
         * Líneas horizontales
         */
        for(int x = juego.ALTO_CELDA; x <= juego.ALTO_CELDA * 2; x += juego.ALTO_CELDA) {
            g2D.drawLine(0, x, juego.ANCHO, x);
        }

        /**
         * Dibuja las fichas
         */
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int celda = juego.getCasillas()[i][j];
                if(celda != juego.LIBRE) {
                    g2D.drawImage(Resources.arrayFichas[celda - 1], i * juego.ANCHO_CELDA, j * juego.ALTO_CELDA,
                            juego.ANCHO_CELDA - 5, juego.ALTO_CELDA - 3, null);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D)g;
        g2D.setStroke(new BasicStroke(10));
        g2D.setColor(Color.yellow);

        FondoVentana fondo = new FondoVentana();
        fondo.setFondo("res/backgroundv2.png");
        g2D.drawImage(fondo.getFondo(), 0, 0, getWidth(), getHeight(), null);

        dibujarContenido(g2D);
    }

    class Input extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) juego.movimiento(e.getX() / juego.ANCHO_CELDA, e.getY() / juego.ALTO_CELDA);
        }
    }
}
