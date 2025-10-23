package modelo;

import gui.ImagenNave;

import java.util.Optional;

public class Nave {

    // Posición y tamaño
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private float tiempoRecarga;
    private float tiempoDesdeUltDisparo;

    private Area areaJuego;

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
        if (tiempoDesdeUltDisparo < tiempoRecarga)
            tiempoDesdeUltDisparo++;
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

    public Optional<Nave> hayColision(Proyectil proyectil) {

        if (proyectil == null) return null;
        if (proyectil.esDelJugador()) return null;

        int px = proyectil.getX();
        int py = proyectil.getY();
        int pAncho = proyectil.getAncho();
        int pAlto = proyectil.getAlto();

        boolean colisionX = px < this.x + this.ancho && px + pAncho > this.x;
        boolean colisionY = py < this.y + this.alto && py + pAlto > this.y;

        if (colisionX && colisionY) {
            return new NaveView(id);
        }
        return null;
    }
}
