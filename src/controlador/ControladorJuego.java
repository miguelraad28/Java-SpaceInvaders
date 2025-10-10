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

    private static ControladorJuego instancia;

    private Nave nave;
    private Area areaJuego;

    private ControladorJuego(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.nave = new Nave(areaJuego.getAncho() / 2 - 50 / 2, 500, 7, 50, 50, areaJuego);
    }

    public static ControladorJuego getInstancia(Area areaJuego) {
        if (instancia == null) {
            instancia = new ControladorJuego(areaJuego);
        } 
        return instancia;
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
