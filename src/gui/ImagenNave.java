package gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImagenNave extends JLabel{
    private final int ancho;
    private final int alto;

    public ImagenNave(){
        ancho = 50;
        alto = 50;

        Image imagen = new ImageIcon(getClass().getResource("/nave.png")).getImage();

        Image imagenAEscala = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        ImageIcon imagenIcono = new ImageIcon(imagenAEscala);

        setIcon(imagenIcono);
    }

    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }

    public void mover(int x, int y){
        // System.out.println("Mover X: " + x + " Y: " + y);
        setBounds(x, y, ancho, alto);
    }
}
