package modelo;

/**
 * Enumeración que representa los niveles de dificultad del juego.
 * Cada dificultad tiene un multiplicador de velocidad diferente.
 */
public enum Dificultad {
    CADETE(2, 1, 40),
    GUERRERO(4, 1.5f, 35), 
    MAESTRO(6, 2.2f, 30);
    
    private final int velocidadInvasor;
    private final float probabilidadDisparoInvasor;
    private final int tiempoRecargaInvasor;

    Dificultad(int velocidadInvasor, float probabilidadDisparoInvasor, int tiempoRecargaInvasor) {
        this.velocidadInvasor = velocidadInvasor;
        this.probabilidadDisparoInvasor = probabilidadDisparoInvasor;
        this.tiempoRecargaInvasor = tiempoRecargaInvasor;
    }
    
    public int getVelocidadInvasor() {
        return velocidadInvasor;
    }

    public float getProbabilidadDisparoInvasor() {
        return probabilidadDisparoInvasor;
    }

    public int getTiempoRecargaInvasor() {
        return tiempoRecargaInvasor;
    }
}
