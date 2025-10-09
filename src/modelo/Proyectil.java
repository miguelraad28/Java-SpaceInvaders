package modelo;

/**
 * Proyectil disparado en el juego.
 */
public class Proyectil {

    // --- Atributos ---
    private float x;
    private float y;
    private float velocidad;
    private boolean delJugador;

    /**
     * Constructor para crear un nuevo proyectil.
     */
    public Proyectil(float x, float y, float velocidad, boolean esDelJugador) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.delJugador = esDelJugador;
    }

    /**
     * Actualiza la posición del proyectil.
     */
    public void actualizar(float dt) {
        this.y += this.velocidad * dt;
    }

    // --- (Getters y Setters) ---

    // Getter para la posición X
    public float getX() {
        return this.x;
    }

    // Setter para la posición X
    public void setX(float x) {
        this.x = x;
    }

    // Getter para la posición Y
    public float getY() {
        return this.y;
    }

    // Setter para la posición Y
    public void setY(float y) {
        this.y = y;
    }

    // Getter para la velocidad (un setter podría no ser necesario si no cambia)
    public float getVelocidad() {
        return this.velocidad;
    }

    // Getter para saber si es del jugador
    public boolean esDelJugador() { // 'is' es una convención común para getters de booleanos
        return this.delJugador;
    }

}