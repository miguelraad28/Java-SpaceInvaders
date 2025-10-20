/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Dificultad;

import modelo.Area;
import modelo.GestorCreditos;
import modelo.Rank;

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
    private List<Rank> ranking;
    
    private Nave nave;
    private Juego juego;

    private ControladorJuego(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.gestorCreditos = new GestorCreditos();
        this.ranking = new ArrayList<Rank>();
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
        gestorCreditos.consumirParaNuevoJuego();

        nave = new Nave(areaJuego.getAncho() / 2 - 50 / 2, 500, 7, 50, 50, areaJuego, 5);
        juego = new Juego(dificultad);
        juego.iniciar();
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

    public int[] disparar() {
        Proyectil proyectil = this.nave.intentarDisparo();
        if (proyectil != null) {
            ControladorProyectiles.getInstancia(areaJuego).agregarProyectil(proyectil);
            return new int[] { proyectil.getProyectilID(), proyectil.getX(), proyectil.getY() };
        }
        return null;
    }

    public void actualizarCooldownNave() {
        this.nave.actualizarCooldownNave();
    }

    public void quitarVida() {
        this.juego.quitarVida();

        if (this.juego.getVidas() <= 0) {
            // todo:
            // Mover nave
        }
    }

    public String obtenerDificultad() {
        return this.juego.getDificultad().name();
    }
    public int obtenerVidas() {
        return this.juego.getVidas();
    }

    public int obtenerPuntaje() {
        return this.juego.getPuntaje();
    }
}
