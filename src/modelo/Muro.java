package modelo;

public class Muro {

    private static int numeradorID = 0;

    private int MuroID;
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private boolean delJugador;
    private float vida;

    /**
     * Constructor para crear un nuevo Muro.
     */
    public Muro(int x, int y, float vida) {
        this.MuroID = numeradorID++;
        this.ancho = 4;
        this.alto = 4;
        this.x = x;
        this.y = y;
        this.vida = vida;
    }

    public int getMuroID() {
        return this.MuroID;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public float getVida() {return this.vida; }

    public boolean estoyRoto(float vida){
        return vida == 0;
    }

}
