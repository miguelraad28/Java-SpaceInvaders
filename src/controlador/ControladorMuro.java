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

    private void hayColision(Muro muro, Proyectil proyectil) {
        if (esDelJugador(proyectil)) {
            impactadoPorJugador(muro);
        } else {
            impactadoPorInvasor(muro);
        }
    }
    private void impactadoPorJugador(Muro muro) {
        float vida = muro.getVida();
        vida = (float) (vida*0.95);
    }

    private void impactadoPorInvasor(Muro muro) {
        float vida = muro.getVida();
        vida = (float) (vida*0.90);
    }

    public Map<Integer, Muro> actualizarMuros() {
        Map<Integer, Muro> murosMap = new HashMap<>();

        // Iterar hacia atrás para poder eliminar elementos de forma segura
        for (int i = muros.size() - 1; i >= 0; i--) {
            Muro m = muros.get(i);
            for (int j = proyectiles.size() - 1; j >= 0; j--) {
                Proyectil p = proyectiles.get(j);
                hayColision(m, p);
                if (m.estoyRoto(m.getVida())){
                    murosMap.put(m.getMuroID(), m);
                }
                else  {
                    this.eliminarMuro(m);
                }
            }
        }

        return murosMap;
    }
    /*
    Esta funcion debe usarse cada vez que actualizo el ciclo de los proyectiles
    Esta funcion recorre por cada muro, si es impactado por cada uno de los proyectiles activos en ese momento
    Luego disminuye la vida, y pregunta si esta roto, de tener vida > 0 lo agrega al map, si es = 0 no lo agrega
    Revisar si hace las cosas bien, la verdad estoy re chapita no me da el bocho
    Revisar en que parte de Panel juego debe implementarse.
    */
}
