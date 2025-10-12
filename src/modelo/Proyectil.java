package modelo;

/**
 * Proyectil disparado en el juego.
 */
public class Proyectil {

    private static int numeradorID = 0;

    private int proyectilID;
    private int x;
    private int y;
    private int velocidad;
    private int ancho;
    private int alto;
    private boolean delJugador;

    /**
     * Constructor para crear un nuevo proyectil.
     */
    public Proyectil(int x, int y, int velocidad, boolean esDelJugador) {
        this.proyectilID = numeradorID++;
        this.ancho = 4;
        this.alto = 4;
        this.x = x - this.ancho / 2;
        this.y = y - this.alto / 2;
        this.velocidad = velocidad;
        this.delJugador = esDelJugador;
    }

    public int getProyectilID() {
        return this.proyectilID;
    }
    
    public int moverY() {
        if(this.delJugador) {
            this.y -= this.velocidad;
        } else {
            this.y += this.velocidad;
        }

        return this.y;
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

    public boolean esDelJugador() {
        return this.delJugador;
    }

    public boolean estoyEnLimites (Area areaJuego) {
        // Sale por límite superior
        if(this.y + this.alto < 0){
            System.out.println("Salí por arriba");
            return false;
        }else if(this.y > areaJuego.getAlto()){
            // Sale por límite inferior
            return false;
        }
        // Está en límites 
        return true;
    }

}