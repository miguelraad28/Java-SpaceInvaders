/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.Dificultad;

import modelo.Area;
import modelo.GestorCreditos;
import modelo.Rank;
import views.MuroView;
import modelo.Juego;
import modelo.Muro;
import modelo.Nave;
import modelo.Proyectil;
import views.NaveView;

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
    private List<Muro> muros;
    private Juego juego;

    private ControladorJuego(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.gestorCreditos = new GestorCreditos();
        this.ranking = new ArrayList<Rank>();
        this.muros = new ArrayList<Muro>();
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

    public Optional<NaveView> hayColisionConNave(Proyectil proyectil) {
        return this.nave.hayColision(proyectil);
    }

    public Optional<MuroView> hayColisionConMuro(Proyectil proyectil) {

        for (Muro muro : this.muros) {
            Optional<MuroView> muroView = muro.hayColision(proyectil);

            if (muroView.isPresent()) {
                return muroView;
            }

        }
        return Optional.empty();
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

    public List<MuroView> iniciarMuros() {
        List<MuroView> murosView = new ArrayList<>();

        // Limpiar lista de muros existente
        this.muros.clear();

        // Dimensiones del área de juego
        int anchoArea = areaJuego.getAncho();
        int altoArea = areaJuego.getAlto();

        // Crear 4 muros distribuidos uniformemente
        int cantidadMuros = 4;
        int anchoMuro = 80; // Ancho de cada muro
        int altoMuro = 60; // Alto de cada muro
        int yMuro = altoArea - 200; // Posición Y fija para todos los muros

        // Calcular espaciado entre muros
        int espacioTotal = anchoArea - (cantidadMuros * anchoMuro);
        int espacioEntreMuros = espacioTotal / (cantidadMuros + 1);

        for (int i = 0; i < cantidadMuros; i++) {
            // Calcular posición X para cada muro
            int xMuro = espacioEntreMuros + i * (anchoMuro + espacioEntreMuros);

            // Crear muro con vida completa
            Muro muro = new Muro(xMuro, yMuro, anchoMuro, altoMuro, 1.0f);
            this.muros.add(muro);

            // Crear vista del muro
            MuroView muroView = new MuroView(
                    muro.getMuroID(),
                    muro.getX(),
                    muro.getY(),
                    muro.getVida());
            murosView.add(muroView);
        }

        return murosView;
    }
}
