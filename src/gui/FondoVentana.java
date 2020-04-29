package gui;

import javax.swing.*;
import java.awt.*;

public class FondoVentana extends JPanel {
    private Image fondo;

    public FondoVentana(){}

    public void setFondo(String imagePath) {
        this.setOpaque(false);
        this.fondo = new ImageIcon(imagePath).getImage();
        repaint();
    }

    public Image getFondo() {
        return fondo;
    }
}
