/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Area;
import modelo.Dificultad;
import modelo.Juego;
import modelo.Nave;
import modelo.Proyectil;

/**
 *
 * @author Dell
 */
public class ControladorJuego {

    private static ControladorJuego instancia;

    private final Nave nave;
    private final Area areaJuego;
    private Juego juego;

    private ControladorJuego(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.nave = new Nave(areaJuego.getAncho() / 2 - 50 / 2, 500, 7, 50, 50, areaJuego, 5);
    }

    public static ControladorJuego getInstancia(Area areaJuego) {
        if (instancia == null) {
            instancia = new ControladorJuego(areaJuego);
        } 
        return instancia;
    }

    public void iniciarJuego(Dificultad dificultad) {
        dificultad.getMultiplicadorVelocidad();

        juego = new Juego(dificultad);
        juego.iniciar();
    }

    public int moverNaveIzquierda() {
        return this.nave.moverIzquierda();
    }

    public int moverNaveDerecha() {
        return this.nave.moverDerecha();
    }

    public Proyectil disparar() {
        return this.nave.intentarDisparo();
    }

    public void actualizarCooldownNave(){
        this.nave.actualizarCooldownNave();
        return;
    }
}
