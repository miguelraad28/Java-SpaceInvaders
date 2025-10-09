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
import modelo.Ranking;

/**
 *
 * @author Dell
 */
public class Controlador {
    private GestorCreditos gestorCreditos;
    private Ranking ranking;
    private Juego juego;
    private VentanaPrincipal ventanaPrincipal;
    private Timer gameTimer;

    public Controlador(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        gestorCreditos = new GestorCreditos();
    }

    public int obtenerCreditos() {
        return gestorCreditos.obtenerSaldo();
    }

    public void iniciarJuego(Dificultad dificultad) {

        boolean tieneCreditos = gestorCreditos.puedeIniciarJuego();

        if (tieneCreditos) {
            gestorCreditos.consumirParaNuevoJuego();
            juego = new Juego(dificultad, ventanaPrincipal.getAncho(), ventanaPrincipal.getAlto());
            juego.iniciar();
            this.ventanaPrincipal.mostrarPanelJuego();
            this.iniciarGameLoop();
        } else {
            ventanaPrincipal.mostrarMensaje("No tienes créditos suficientes.\n Por favor ingrese créditos.");
        }

    }

    private void iniciarGameLoop() {
        gameTimer = new Timer(16, e -> {
            if (juego != null && juego.estoyEnCurso()) {
                // Actualizar modelo
                juego.actualizar();
                // Actualizar vista
                ventanaPrincipal.actualizarPanelJuego();
            } else {
                // Juego terminó
                detenerGameLoop();
                // terminar(false);
            }
        });
        gameTimer.start();
    }

    private void detenerGameLoop() {
        if (gameTimer != null) {
            gameTimer.stop();
            gameTimer = null;
        }
    }

    public void moverNaveIzq() {
        System.out.println("Moviendo nave a la izquierda");
    }

    public void moverNaveDer() {
        System.out.println("Moviendo nave a la derecha");
    }

    public void disparar() {
        System.out.println("Disparando");
    }
}
