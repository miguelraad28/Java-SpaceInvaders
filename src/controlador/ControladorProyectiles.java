package controlador;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Area;
import modelo.Proyectil;

public class ControladorProyectiles {

    private static ControladorProyectiles instancia;
    private List<Proyectil> proyectiles;
    private Area areaJuego;

    private ControladorProyectiles(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.proyectiles = new ArrayList<>();
    }

    public static ControladorProyectiles getInstancia(Area areaJuego) {
        if (instancia == null) {
            instancia = new ControladorProyectiles(areaJuego);
        }
        return instancia;
    }

    public void agregarProyectil(Proyectil proyectil) {
        this.proyectiles.add(proyectil);
    }

    public Map<Integer, Proyectil> moverProyectiles() {
        Map<Integer, Proyectil> proyectilesMap = new HashMap<>();
        // Iterar hacia atrás para poder eliminar elementos de forma segura
        for (int i = proyectiles.size() - 1; i >= 0; i--) {
            Proyectil proyectil = proyectiles.get(i);
            proyectil.moverY();

            boolean estaEnLimites = proyectil.estoyEnLimites(areaJuego);

            if(!estaEnLimites){
                this.eliminarProyectil(proyectil);
            }else{
                boolean hayColision = this.hayColision(proyectil);
                // Verificar que no haya colisionado o salido del mapa.
                if(!hayColision){
                    proyectilesMap.put(proyectil.getProyectilID(), proyectil);
                } else {
                    this.eliminarProyectil(proyectil);
                }
            }
            
        }

        return proyectilesMap;
    }

    private boolean hayColision(Proyectil proyectil) {
        if (proyectil.esDelJugador()) {
            // Ver si hay muro o invasor
            return false;
        } else {
            // Ver si hay muro o nave
            return false;
        }
    }

    private void eliminarProyectil(Proyectil p) {
        this.proyectiles.remove(p);
    }
}
