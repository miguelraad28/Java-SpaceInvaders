package modelo;

/**
 * Enumeración que representa los niveles de dificultad del juego.
 * Cada dificultad tiene un multiplicador de velocidad diferente.
 */
public enum Dificultad {
    CADETE(2, 1, 40),
    GUERRERO(4, 2, 30), 
    MAESTRO(6, 4, 25);
    
    private final int velocidadInvasor;
    private final int probabilidadDisparoInvasor;
    private final int tiempoRecargaInvasor;

    Dificultad(int velocidadInvasor, int probabilidadDisparoInvasor, int tiempoRecargaInvasor) {
        this.velocidadInvasor = velocidadInvasor;
        this.probabilidadDisparoInvasor = probabilidadDisparoInvasor;
        this.tiempoRecargaInvasor = tiempoRecargaInvasor;
    }
    
    public int getVelocidadInvasor() {
        return velocidadInvasor;
    }

    public int getProbabilidadDisparoInvasor() {
        return probabilidadDisparoInvasor;
    }

    public int getTiempoRecargaInvasor() {
        return tiempoRecargaInvasor;
    }
}
