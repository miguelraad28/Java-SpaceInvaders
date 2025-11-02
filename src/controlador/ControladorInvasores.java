package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Area;
import modelo.Dificultad;
import modelo.Invasor;
import modelo.Proyectil;

public class ControladorInvasores {

    private static ControladorInvasores instancia;
    private Area areaJuego;
    private List<Invasor> invasores;

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

    public boolean hayColisionConInvasor(Proyectil proyectil) {
        for (Invasor invasor : invasores) {
            // Verificar colisión usando coordenadas
            int px = proyectil.getX();
            int py = proyectil.getY();
            int pw = proyectil.getAncho();
            int ph = proyectil.getAlto();

            int ix = invasor.getX();
            int iy = invasor.getY();
            int iw = invasor.getAncho();
            int ih = invasor.getAlto();

            // Verificar si hay intersección
            if (px < ix + iw && px + pw > ix &&
                    py < iy + ih && py + ph > iy) {
                // Colisión detectada, destruir invasor
                this.invasores.remove(invasor);
                return true;
            }
        }
        return false;
    }

    public int[][] iniciarInvasores(Dificultad dificultad) {
        int[][] posiciones = new int[15][2];

        int filas = 3;
        int columnas = 5;
        int separacionHorizontal = 20; // espacio entre invasores en X
        int separacionVertical = 20; // espacio entre invasores en Y
        String tipoInvasor = "Basico";
        int anchoInvasor = 53;
        int altoInvasor = 39;
        int velocidad = dificultad.getVelocidadInvasor();
        int probabilidadDisparar = dificultad.getProbabilidadDisparoInvasor();
        int tiempoRecarga = dificultad.getTiempoRecargaInvasor();

        int anchoGrid = columnas * anchoInvasor + (columnas - 1) * separacionHorizontal;

        int inicioX = (areaJuego.getAncho() - anchoGrid) / 2;
        int inicioY = 60; // margen superior para que no queden pegados al borde

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = inicioX + col * (anchoInvasor + separacionHorizontal);
                int y = inicioY + fila * (altoInvasor + separacionVertical);
                Invasor invasor = new Invasor(x, y, anchoInvasor, altoInvasor, velocidad, probabilidadDisparar,
                        tiempoRecarga, areaJuego, tipoInvasor);

                posiciones[fila * columnas + col][0] = x;
                posiciones[fila * columnas + col][1] = y;
                invasores.add(invasor);
            }
        }

        return posiciones;
    }

    public void moverInvasores() {
        boolean necesitoCambiarDireccion = false;

        for (int i = 0; i < this.invasores.size(); i++) {
            Invasor invasor = this.invasores.get(i);

            int[] resultado = invasor.mover();

            if (resultado[2] == 1) {
                necesitoCambiarDireccion = true;
            }
        }

        // Cambiamos direccion si es necesario
        // para el próximo tick.
        if (necesitoCambiarDireccion) {
            Invasor.cambiarDireccion();
            for (Invasor invasor : invasores) {
                invasor.setY(invasor.getY() + 10);
            }
        }
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
