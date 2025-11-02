package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import modelo.Area;
import modelo.Dificultad;
import modelo.Invasor;
import modelo.Proyectil;
import views.InvasorView;

public class ControladorInvasores {

    private static ControladorInvasores instancia;
    private Area areaJuego;
    private List<Invasor> invasores;
    private int direccion = 1;

    private ControladorInvasores(Area areaJuego) {
        this.areaJuego = areaJuego;
        this.invasores = new ArrayList<>();
    }

    public static ControladorInvasores getInstancia(Area areaJuego) {
        if (instancia == null) {
            instancia = new ControladorInvasores(areaJuego);
        }
        return instancia;
    }

    public Optional<InvasorView> hayColisionConInvasor(Proyectil proyectil) {
        for (int i = 0; i < invasores.size(); i++) {
            Invasor invasor = invasores.get(i);
            // Revisa si hay colisión por c/u
            Optional<InvasorView> invasorView = invasor.hayColision(proyectil);
            // Como un proyectil solo puede colisionar con 1 cosa a la vez
            // si hay colision, eliminamos y retornamos inmediatamente
            if (invasorView.isPresent()) {
                this.invasores.remove(invasor);
                return invasorView;
            }
        }

        return Optional.empty();
    }

    public List<InvasorView> iniciarInvasores(Dificultad dificultad) {
        List<InvasorView> invasorViews = new ArrayList<>();

        int filas = 3;
        int columnas = 5;
        int separacionHorizontal = 20; // espacio entre invasores en X
        int separacionVertical = 20; // espacio entre invasores en Y
        int anchoInvasor = 53;
        int altoInvasor = 39;
        int velocidad = dificultad.getVelocidadInvasor();
        float probabilidadDisparar = dificultad.getProbabilidadDisparoInvasor();
        int tiempoRecarga = dificultad.getTiempoRecargaInvasor();

        int anchoGrid = columnas * anchoInvasor + (columnas - 1) * separacionHorizontal;

        int inicioX = (areaJuego.getAncho() - anchoGrid) / 2;
        int inicioY = 60; // margen superior para que no queden pegados al borde

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = inicioX + col * (anchoInvasor + separacionHorizontal);
                int y = inicioY + fila * (altoInvasor + separacionVertical);
                Invasor invasor = new Invasor(x, y, anchoInvasor, altoInvasor, velocidad, probabilidadDisparar,
                        tiempoRecarga, areaJuego);

                invasorViews.add(new InvasorView(invasor.getInvasorID(), x, y));
                invasores.add(invasor);
            }
        }

        return invasorViews;
    }

    public List<InvasorView> moverInvasores() {
        List<InvasorView> invasorViews = new ArrayList<>();

        boolean necesitoCambiarDireccion = false;

        for (int i = 0; i < this.invasores.size(); i++) {
            Invasor invasor = this.invasores.get(i);

            InvasorView invasorView = invasor.mover(this.direccion);

            if (this.direccion == 1) {
                if (invasorView.getX() + invasorView.getAncho() >= areaJuego.getAncho()) {
                    necesitoCambiarDireccion = true;
                }
            } else {
                if (invasorView.getX() <= 0) {
                    necesitoCambiarDireccion = true;
                }
            }

            invasorViews.add(invasorView);
        }

        // Cambiamos direccion si es necesario
        // para el próximo tick.
        if (necesitoCambiarDireccion) {
            this.cambiarDireccion();

            int variacionDeAltura = 10;

            for (Invasor invasor : invasores) {
                invasor.setY(invasor.getY() + variacionDeAltura);
            }

            for (InvasorView invasorView : invasorViews) {
                invasorView.setY(invasorView.getY() + variacionDeAltura);
            }
        }

        return invasorViews;
    }

    private void cambiarDireccion() {
        this.direccion = -1 * this.direccion;
    }

    public Map<Integer, int[]> disparoDeInvasores() {
        Map<Integer, int[]> proyectilesMap = new HashMap<>();

        for (int i = 0; i < this.invasores.size(); i++) {
            Invasor invasor = this.invasores.get(i);
            Proyectil proyectil = invasor.disparar();
            if (proyectil != null) {
                ControladorProyectiles.getInstancia(areaJuego).agregarProyectil(proyectil);
                proyectilesMap.put(proyectil.getProyectilID(), new int[] { proyectil.getX(), proyectil.getY() });
            }
        }

        return proyectilesMap;
    }

    public void actualizarCooldownInvasor() {
        for (int i = 0; i < this.invasores.size(); i++) {
            Invasor invasor = this.invasores.get(i);
            invasor.actualizarCooldownInvasor();
        }
    }
}
