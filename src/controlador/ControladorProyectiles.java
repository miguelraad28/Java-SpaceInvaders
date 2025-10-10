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

            proyectilesMap.put(proyectil.getProyectilID(), proyectil);

            this.evaluarColision(proyectil);
        }

        return proyectilesMap;
    }

    private void evaluarColision(Proyectil proyectil) {
        if (proyectil.getY() < 0 || proyectil.getY() > areaJuego.getAlto()) {
            this.eliminarProyectil(proyectil);
        }

        if (proyectil.esDelJugador()) {

        } else {

            this.eliminarProyectil(proyectil);
        }

    }

    private void eliminarProyectil(Proyectil p) {
        this.proyectiles.remove(p);
    }
}
