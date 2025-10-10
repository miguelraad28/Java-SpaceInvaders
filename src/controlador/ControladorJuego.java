/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.Timer;

import gui.VentanaPrincipal;
import modelo.Dificultad;
import modelo.GestorCreditos;
import modelo.Juego;
import modelo.Area;
import modelo.Nave;
import modelo.Proyectil;
import modelo.Ranking;

/**
 *
 * @author Dell
 */
public class ControladorJuego {
    private Nave nave;
    private Area areaJuego;

    public ControladorJuego(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.nave = new Nave(areaJuego.getAncho() / 2 - 50 / 2, 200, 10, 50, 50, areaJuego);
    }

    public int moverNaveIzquierda() {
        System.out.println("Mover izquierda");
        return this.nave.moverIzquierda();
    }

    public int moverNaveDerecha() {
        System.out.println("Mover derecha");
        return this.nave.moverDerecha();
    }

    public Proyectil disparar() {
        return this.nave.intentarDisparo();
    }
}
