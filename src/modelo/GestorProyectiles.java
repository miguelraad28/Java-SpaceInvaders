/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Administra todos los proyectiles activos en el juego.
 */
public class GestorProyectiles {

    // El atributo sigue siendo privado, lo cual es correcto.
    private List<Proyectil> proyectiles;

    public GestorProyectiles() {
        this.proyectiles = new ArrayList<>();
    }

    /**
     * Agrega un nuevo proyectil a la lista de gestión.
     */
    public void agregarProyectil(Proyectil p) {
        this.proyectiles.add(p);
    }

    /**
     * Elimina un proyectil específico de la lista.
     */
    public void eliminarProyectil(Proyectil p) {
        this.proyectiles.remove(p);
    }

    /**
     * Actualiza el estado de todos los proyectiles.
     */
    public void actualizar(float dt, MuroEnergia muro, Nave nave, Juego juego, Area area, Oleada oleada) {
        Iterator<Proyectil> iterador = proyectiles.iterator();

        while (iterador.hasNext()) {
            Proyectil p = iterador.next();
            // Llama al método de actualización del proyectil
            p.actualizar(dt);

            // CAMBIO: Se usan los getters p.getX() y p.getY()
            if (!area.estaEnLimites(p.getX(), p.getY())) {
                iterador.remove();
                continue; // Pasa al siguiente proyectil
            }

            // CAMBIO: Se usa el getter p.isDelJugador() para la comprobación
            if (p.esDelJugador()) {
                // --- LÓGICA PARA PROYECTILES DEL JUGADOR ---

                // Comprobar colisión con el muro
                if (muro.soyElMuro(p.getX(), p.getY())) {
                    muro.impactadoPorJugador();
                    iterador.remove();
                    continue;
                }
                // Comprobar colisión con una nave invasora
                Invasor invasorImpactado = oleada.colisionaConInvasor(p);
                if (invasorImpactado != null) {
                    invasorImpactado.destruir();
                    juego.sumarPuntaje(10);
                    iterador.remove();
                }
            } else {
                // --- LÓGICA PARA PROYECTILES DEL INVASOR ---

                // Comprobar colisión con el muro
                if (muro.soyElMuro(p.getX(), p.getY())) {
                    muro.impactadoPorInvasor(p);
                    iterador.remove();
                    continue;
                }
                // Comprobar colisión con la nave del jugador
                if (nave.colisionaCon(p)) {
                    juego.perderVida();
                    iterador.remove();
                }
            }
        }
    }
}