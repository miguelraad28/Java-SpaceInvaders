/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package modelo;

public class Invasor {

    private int x;
    private int y;
    private int ancho;
    private int altura;

    private int velocidad;
    private int direccion = 1;
    private Area areaJuego;

    private float probabilidadDisparar;
    private int cooldown;
    private int ultDisparo;

    public Invasor(int x, int y, int ancho, int altura, int velocidad,
                   float probabilidadDisparar, int cooldown, Area areaJuego) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.altura = altura;
        this.velocidad = velocidad;
        this.probabilidadDisparar = probabilidadDisparar;
        this.cooldown = cooldown;
        this.ultDisparo = cooldown;
        this.areaJuego = areaJuego;
    }

    public void actualizar() {
        if (ultDisparo < cooldown) ultDisparo++;
    }


    public int[] mover() {
        int nuevoX = x + direccion * velocidad;
        boolean tocoBorde = false;

        if (nuevoX < 0) {
            x = 0;
            tocoBorde = true;
        } else if (nuevoX + ancho > areaJuego.getAncho()) {
            x = areaJuego.getAncho() - ancho;
            tocoBorde = true;
        } else {
            x = nuevoX;
        }

        return new int[]{x, y, tocoBorde ? 1 : 0};
    }

    public void cambiarDireccion() {
        direccion = -direccion;
    }

    public void bajar(int pixeles) {
        y += pixeles;
    }

    public boolean puedeDisparar() {
        return ultDisparo >= cooldown;
    }

    public Proyectil intentarDisparo() {
        if (!puedeDisparar()) return null;

        double random = Math.random();
        if (random <= probabilidadDisparar) {
            ultDisparo = 0;

            int px = x + ancho / 2;
            int py = y + altura; // desde la parte inferior
            int velocidadProyectil = 4;

            return new Proyectil(px, py, velocidadProyectil, false);
        }
        return null;
    }
}
