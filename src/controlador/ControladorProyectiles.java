package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelo.Area;
import modelo.Proyectil;
import views.InvasorView;
import views.MuroView;
import views.NaveView;
import views.ObjetoImpactado;
import views.ProyectilView;

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

    public List<ProyectilView> moverProyectiles() {
        List<ProyectilView> proyectilesView = new ArrayList<>();

        // Iterar hacia atrás para poder eliminar elementos de forma segura
        for (int i = proyectiles.size() - 1; i >= 0; i--) {
            Proyectil proyectil = proyectiles.get(i);
            proyectil.moverY();

            boolean estaEnLimites = proyectil.estoyEnLimites(areaJuego);

            if (!estaEnLimites) {
                this.eliminarProyectil(proyectil);
            } else {
                // Cambiar a una interfaz
                Optional<ObjetoImpactado> objetoImpactado = this.hayColision(proyectil);
                // Verificar que no haya colisionado o salido del mapa.
                ProyectilView proyectilView = new ProyectilView(proyectil.getProyectilID(), proyectil.getX(),
                        proyectil.getY(), objetoImpactado);
                proyectilesView.add(proyectilView);

                if (objetoImpactado.isPresent()) {
                    this.eliminarProyectil(proyectil);
                }
            }

        }

        return proyectilesView;
    }

    private Optional<ObjetoImpactado> hayColision(Proyectil proyectil) {
        // Ver si hay muro o invasor
        Optional<MuroView> muroView = ControladorJuego.getInstancia(areaJuego).hayColisionConMuro(proyectil);

        if (muroView.isPresent()) {
            return Optional.of(muroView.get());
        }

        if (proyectil.esDelJugador()) {
            // return Optional.empty();

            Optional<InvasorView> invasorView = ControladorInvasores.getInstancia(areaJuego)
                    .hayColisionConInvasor(proyectil);

            if (invasorView.isPresent()) {

                return Optional.of(invasorView.get());
            }
            
            return Optional.empty();
        } else {
            // Ver si hay muro o nave
            Optional<NaveView> naveView = ControladorJuego.getInstancia(areaJuego).hayColisionConNave(proyectil);

            if (naveView.isPresent()) {
                return Optional.of(naveView.get());
            }

            return Optional.empty();
        }
    }

    private void eliminarProyectil(Proyectil p) {
        this.proyectiles.remove(p);
    }

    public void resetear() {
        this.proyectiles.clear();
    }
}
