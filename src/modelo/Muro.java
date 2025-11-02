package modelo;

import java.util.Optional;
import views.MuroView;

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
    public Muro(int x, int y, int ancho, int alto) {
        this.MuroID = numeradorID++;
        this.ancho = ancho;
        this.alto = alto;
        this.x = x;
        this.y = y;
        this.vida = 1.00f;
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

    public float getVida() {
        return this.vida;
    }

    private void impactadoPorJugador() {
        this.vida = (float) (this.vida - 0.05);
    }

    private void impactadoPorInvasor() {
        this.vida = (float) (this.vida - 0.1);
    }

    public Optional<MuroView> hayColision(Proyectil p) {

        int px = p.getX();
        int py = p.getY();
        int pAncho = p.getAncho();
        int pAlto = p.getAlto();
        // System.out.println("Es del jugador: " + p.esDelJugador());
        if (p.esDelJugador() && px >= this.getX() && px <= this.getX() + this.getAncho()
                && py <= this.getY() + this.getAlto()) {

            this.impactadoPorJugador();

            return Optional.of(new MuroView(this.getMuroID(), this.getX(), this.getY(), this.getVida()));
        } else if (!p.esDelJugador() && px + pAncho >= this.getX() && px <= this.getX() + this.getAncho()
                && py + pAlto >= this.getY()) {

            this.impactadoPorInvasor();

            return Optional.of(new MuroView(this.getMuroID(), this.getX(), this.getY(), this.getVida()));
        }

        return Optional.empty();
    }
}
