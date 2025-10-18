/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Area;
import modelo.Dificultad;
import modelo.GestorCreditos;
import modelo.Juego;
import modelo.Nave;
import modelo.Proyectil;

/**
 *
 * @author Dell
 */
public class ControladorJuego {

    private static ControladorJuego instancia;

    private final Area areaJuego;
    private final GestorCreditos gestorCreditos;
    private Nave nave;
    private Juego juego;

    private ControladorJuego(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.gestorCreditos = new GestorCreditos();
    }

    public static ControladorJuego getInstancia(Area areaJuego) {
        if (instancia == null) {
            instancia = new ControladorJuego(areaJuego);
        } 
        return instancia;
    }

    public void cargar(int cantidad) {
        this.gestorCreditos.cargar(cantidad);
    }

    public int obtenerSaldo() {
        return this.gestorCreditos.obtenerSaldo();
    }

    public void iniciarJuego(Dificultad dificultad) {
        dificultad.getMultiplicadorVelocidad();
        
        nave = new Nave(areaJuego.getAncho() / 2 - 50 / 2, 500, 7, 50, 50, areaJuego, 5);
        juego = new Juego(dificultad);
        juego.iniciar();
        gestorCreditos.consumirParaNuevoJuego();
    }

    public boolean hayColisionConNave(Proyectil proyectil) {
        return this.nave.hayColision(proyectil);
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
    }

    public void quitarVida() {
        this.juego.quitarVida();

        if(this.juego.obtenerVidas() <= 0) {
            // todo:
            // Mover nave
        }
    }
}
