package controlador;

import java.util.List;
import java.util.ArrayList;

import modelo.Area;
import modelo.Proyectil;

public class ControladorProyectiles {

    private List<Proyectil> proyectiles;
    private Area areaJuego;
    private static ControladorProyectiles instancia;

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

    
}
