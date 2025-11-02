package gui;

import java.awt.Color;
import javax.swing.JLabel;

public class ImagenMuro extends JLabel {
    private final int muroID;
    private final int ancho;
    private final int alto;
    private float vida;

    public ImagenMuro(int muroID) {
        this.muroID = muroID;
        ancho = 80;
        alto = 20;
        vida = 1;
        
        setOpaque(true);
        actualizarColor();
    }

    public int getMuroID() {
        return muroID;
    }

    public int getAncho() {
        return ancho;
    }
    
    public int getAlto() {
        return alto;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float nuevaVida) {
        this.vida = nuevaVida;
        actualizarColor();
    }

    public boolean estaRoto() {
        return vida <= 0;
    }

    private void actualizarColor() {
        if (vida > 0.7f) {
            setBackground(Color.GREEN); // Muro sano
        } else if (vida > 0.4f) {
            setBackground(Color.YELLOW); // Muro dañado
        } else {
            setBackground(Color.RED); // Muro muy dañado
        }
    }

    public void mover(int x, int y) {
        // System.out.println("Mover Muro X: " + x + " Y: " + y);
        setBounds(x, y, ancho, alto);
    }
}
