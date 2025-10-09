package modelo;

/**
 * Enumeración que representa los niveles de dificultad del juego.
 * Cada dificultad tiene un multiplicador de velocidad diferente.
 */
public enum Dificultad {
    CADETE(1.0f),
    GUERRERO(1.5f),
    MAESTRO(2.0f);
    
    private final float multiplicadorVelocidad;
    
    Dificultad(float multiplicadorVelocidad) {
        this.multiplicadorVelocidad = multiplicadorVelocidad;
    }
    
    public float getMultiplicadorVelocidad() {
        return multiplicadorVelocidad;
    }
}
