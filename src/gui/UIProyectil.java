package gui;

import java.awt.Color;
import javax.swing.JLabel;

public class UIProyectil extends JLabel {
    private int proyectilID;
    private int ancho;
    private int alto;

    public UIProyectil(int proyectilID, boolean esDelJugador) {
        this.proyectilID = proyectilID;
        ancho = 4;
        alto = 4;
        // Colorear el proyectil dependiendo de si es del jugador o no
        if (esDelJugador) {
            System.out.println("UProyectil del jugador creado");
            setBackground(Color.GREEN);
        } else {
            // System.out.println("UProyectil del invasor creado");
            setBackground(Color.RED);
        }
        setOpaque(true); // Necesario para que el color de fondo sea visible
    }

    public boolean soyElProyectil(int proyectilID) {
        return this.proyectilID == proyectilID;
    }

    public void mover(int x, int y) {
        setBounds(x, y, ancho, alto);
    }

    public int getProyectilID() {
        return this.proyectilID;
    }

    public void setProyectilID(int proyectilID) {
        this.proyectilID = proyectilID;
    }

}