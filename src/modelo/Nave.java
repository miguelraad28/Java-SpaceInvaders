package modelo;

public class Nave {

    // Posición y tamaño
    private int x;
    private int y;
    private int ancho;
    private int alto;

    // Movimiento
    private int velocidad;
    private Area areaJuego;

    // Disparo
    private float tiempoRecarga;
    private float tiempoDesdeUltDisparo;

    public Nave(int x, int y, int velocidad, int ancho, int alto, Area areaJuego, float tiempoRecarga) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        this.areaJuego = areaJuego;
        this.tiempoRecarga = tiempoRecarga;
        this.tiempoDesdeUltDisparo = tiempoRecarga;
    }

    public void actualizarCooldownNave() {
        tiempoDesdeUltDisparo += 1;
    }

    public int moverIzquierda() {
        this.x -= this.velocidad;
        if (this.x < 0) this.x = 0;
        return this.x;
    }

    public int moverDerecha() {
        if (this.x + this.velocidad + this.ancho < areaJuego.getAncho()) {
            this.x += this.velocidad;
        } else {
            this.x = areaJuego.getAncho() - this.ancho;
        }
        return this.x;
    }

    // Puede disparar?
    public boolean puedeDisparar() {
        return tiempoDesdeUltDisparo >= tiempoRecarga;
    }

    //
    public Proyectil intentarDisparo() {
        if (!puedeDisparar()) return null;
        tiempoDesdeUltDisparo = 0;


        int px = this.x + this.ancho / 2;
        int py = this.y;
        int velocidadProyectil = 4;

        return new Proyectil(px, py, velocidadProyectil, true);
    }
}
