/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelo.Area;
import modelo.Dificultad;
import modelo.GestorCreditos;
import modelo.Juego;
import modelo.Muro;
import modelo.Nave;
import modelo.Proyectil;
import modelo.Rank;
import views.MuroView;
import views.NaveView;
import views.ProyectilView;
import views.RankView;

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

        muros = new ArrayList<>();
        nave = new Nave(areaJuego.getAncho() / 2 - 50 / 2, 500, 7, 50, 50, areaJuego, 10);
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
                // Si la vida del muro llegó a 0 o menos, eliminarlo de la lista
                if (muro.getVida() <= 0) {
                    this.muros.remove(muro);
                }

                return Optional.of(muroView.get());
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

    public Optional<ProyectilView> disparar() {
        Optional<Proyectil> proyectil = this.nave.intentarDisparo();

        if (proyectil.isPresent()) {
            Proyectil proyectilDeNegocio = proyectil.get();
            ControladorProyectiles.getInstancia(areaJuego).agregarProyectil(proyectilDeNegocio);

            return Optional.of(new ProyectilView(proyectilDeNegocio.getProyectilID(), proyectilDeNegocio.getX(),
                    proyectilDeNegocio.getY(), null));
        }

        return Optional.empty();
    }

    public void actualizarCooldownNave() {
        this.nave.actualizarCooldownNave();
    }

    public void quitarVida() {
        this.juego.quitarVida();
    }

    public String obtenerDificultad() {
        return this.juego.getDificultad().name();
    }

    public Dificultad getDificultadActual() {
        return this.juego.getDificultad();
    }

    public int obtenerVidas() {
        return this.juego.getVidas();
    }

    public int obtenerPuntaje() {
        return this.juego.getPuntaje();
    }

    public void sumarPuntaje(int puntaje) {
        this.juego.sumarPuntaje(puntaje);
    }

    public List<MuroView> iniciarMuros() {
        // Limpiar lista de muros existente
        this.muros.clear();

        List<MuroView> murosView = new ArrayList<>();


        // Dimensiones del área de juego
        int anchoArea = areaJuego.getAncho();
        int altoArea = areaJuego.getAlto();

        // Crear 4 muros distribuidos uniformemente
        int cantidadMuros = 4;
        int anchoMuro = 80; // Ancho de cada muro
        int altoMuro = 20; // Alto de cada muro
        int yMuro = altoArea - 150; // Posición Y fija para todos los muros

        // Calcular espaciado entre muros
        int espacioTotal = anchoArea - (cantidadMuros * anchoMuro);
        int espacioEntreMuros = espacioTotal / (cantidadMuros + 1);

        for (int i = 0; i < cantidadMuros; i++) {
            // Calcular posición X para cada muro
            int xMuro = espacioEntreMuros + i * (anchoMuro + espacioEntreMuros);

            // Crear muro con vida completa
            Muro muro = new Muro(xMuro, yMuro, anchoMuro, altoMuro);
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

    public void registrarPuntaje(String nombre) {
        int puntajeActual = this.obtenerPuntaje();

        // Buscar si ya existe entrada del jugador
        Rank existente = null;
        for (Rank r : ranking) {
            if (r.soyElRanking(nombre)) {
                existente = r;
                break;
            }
        }

        if (existente == null) {
            ranking.add(new Rank(nombre, puntajeActual));
        } else {
            existente.actualizarPuntaje(puntajeActual);
        }
    }

    public List<RankView> obtenerRanking() {
        // Devolver una copia ordenada desc por puntaje como RankView
        List<Rank> copia = new ArrayList<>(ranking);
        copia.sort((a, b) -> Integer.compare(b.getMayorPuntaje(), a.getMayorPuntaje()));
        
        List<RankView> rankingView = new ArrayList<>();
        for (Rank r : copia) {
            rankingView.add(new RankView(r.getNombre(), r.getMayorPuntaje()));
        }
        return rankingView;
    }

    public void resetearEstado() {
        // Limpiar entidades de juego
        this.muros = null;
        this.nave = null;
        this.juego = null;
        // Limpiar controladores dependientes
        ControladorInvasores.getInstancia(areaJuego).resetear();
        ControladorProyectiles.getInstancia(areaJuego).resetear();
    }

    public void centrarNave() {
        if (this.nave != null) {
            this.nave.moverACentro();
        }
    }

    public Dificultad avanzarDificultad() {
        if (this.juego == null) return Dificultad.CADETE;

        Dificultad actual = this.juego.getDificultad();
        Dificultad siguiente;
        // Para maestro (que termina el juego)
        // lo evalua la UI
        switch (actual) {
            case CADETE:
                siguiente = Dificultad.GUERRERO;
                break;
            case GUERRERO:
            default:
                siguiente = Dificultad.MAESTRO;
                break;
        }
        this.juego.siguienteNivel(siguiente);
        return siguiente;
    }
}
