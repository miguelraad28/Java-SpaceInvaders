package modelo;

import java.util.Optional;

public class Muro {

    private static int numeradorID = 0;

    private int MuroID;
    private int x;
    private int y;
    private int ancho;
    private int alto;
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

    private void setVida(float vida){
    if(vida > 0){
        this.vida = vida;
    }
}

    public boolean estoyRoto(){
        return vida == 0;
    }

    private void impactadoPorJugador(){
        vida = (float) (this.vida - 0.05);
    }

    private void impactadoPorInvasor() {
        vida = (float) (this.vida - 0.1);
    }

    public Optional<Muro> hayColision(Muro m, Proyectil p) {
        if (p.esDelJugador()) {
            if (p.getX() >= this.getX() && p.getX() <= this.getX() + this.getAncho() && p.getY() >= this.getY() && p.getY() <= this.getY() + this.getAlto()) {
                m.impactadoPorJugador();
                return new MuroView(m.MuroID, m.getVida(), m.getX(), m.getY());
            } else if (p.getX() >= this.getX() && p.getX() <= this.getX() + this.getAncho() && p.getY() + p.getAlto() >= this.getY()) {
                m.impactadoPorInvasor();
            }
            return new MuroView(m.MuroID, m.getVida(), m.getX(), m.getY());
        else{
                return null;
            }
        }
    }
}
