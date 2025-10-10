/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dell
 */
public class Nave {
    private int x;
    private int y;
    private int velocidad;
    private int ancho;
    private int alto;
    private Area areaJuego;

    public Nave(int x, int y, int velocidad, int ancho, int alto, Area areaJuego) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        this.areaJuego = areaJuego;
    }

    public int moverIzquierda() {
        if (this.x - this.velocidad > 0)
            this.x -= this.velocidad;
        else
            this.x = 0;
        return this.x;
    }

    public int moverDerecha() {
        if (this.x + this.velocidad + this.ancho < areaJuego.getAncho())
            this.x += this.velocidad;
        // else
        //     // Mover justo al borde
        //     this.x = areaJuego.getAncho() - this.ancho;
        return this.x;
    }

    public Proyectil intentarDisparo() {
        return null;
    }
}
