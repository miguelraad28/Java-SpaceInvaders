package gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImagenInvasor extends JLabel{
    private final int invasorID;
    private final int ancho;
    private final int alto;

    public ImagenInvasor(int invasorID){
        this.invasorID = invasorID;
        ancho = 53;
        alto = 39;

        Image imagen = new ImageIcon(getClass().getResource("/invasor.png")).getImage();

        Image imagenAEscala = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        ImageIcon imagenIcono = new ImageIcon(imagenAEscala);

        setIcon(imagenIcono);
    }

    public int getInvasorID(){
        return invasorID;
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
