package controlador;

import modelo.Area;
import modelo.Muro;
import modelo.Nave;
import modelo.Proyectil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorMuro {

    private static ControladorMuro instancia;

    private List<Muro> muros;
    private Area areaJuego;
    private List<Proyectil> proyectiles;

    private ControladorMuro(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.muros = new ArrayList<>();
        this.proyectiles = new ArrayList<>();

    }

    public static ControladorMuro getInstancia(Area areaJuego) {
        if (instancia == null) {
            instancia = new ControladorMuro(areaJuego);
        }
        return instancia;
    }

    public void restaurarMuro(Muro muro) {
        this.muros.add(muro);
    } // Esta funcion debe utilizarse cuando se inicia el juego

    private void eliminarMuro(Muro muro) {
        this.muros.remove(muro);
    }


    private boolean esDelJugador(Proyectil proyectil) {
        return proyectil.esDelJugador();
    }



    private boolean hayColision(Muro muro, Proyectil proyectil) {
        if (esDelJugador(proyectil)) {
            impactadoPorJugador(muro);
        } else {
            impactadoPorInvasor(muro);
        }
    }
    private void impactadoPorJugador
            (Muro muro) {
        float vida = muro.getVida();
        vida = (float) (vida*0.95);
    }

    private void impactadoPorInvasor(Muro muro) {
        float vida = muro.getVida();
        vida = (float) (vida*0.90);
    }
    /*
    ELIMINAR ESTA CLASE
    */
}
