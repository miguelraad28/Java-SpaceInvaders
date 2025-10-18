package controlador;

import java.util.ArrayList;
import java.util.List;
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
        return false;
    }

    public int[][] iniciarInvasores(Dificultad dificultad) {
        int[][] posiciones = new int[15][2];

        int filas = 3;
        int columnas = 5;
        int separacionHorizontal = 20; // espacio entre invasores en X
        int separacionVertical = 20;   // espacio entre invasores en Y

        Invasor invasor = new Invasor();
        int anchoInvasor = invasor.getAncho();
        int altoInvasor = invasor.getAlto();

        int anchoGrid = columnas * anchoInvasor + (columnas - 1) * separacionHorizontal;

        int inicioX = (areaJuego.getAncho() - anchoGrid) / 2;
        int inicioY = 60; // margen superior para que no queden pegados al borde

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = inicioX + col * (anchoInvasor + separacionHorizontal);
                int y = inicioY + fila * (altoInvasor + separacionVertical);
                invasor.mover(x, y);
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
            int x = resultado[0];
            int y = resultado[1];
            boolean tocoBorde = resultado[2] == 1;
            if (tocoBorde) {
                necesitoCambiarDireccion = true;
            }
        }

        // Cambiamos direccion si es necesario
        // para el próximo tick.
        if (necesitoCambiarDireccion) {
            Invasor.direccion = -1 * Invasor.direccion;
        }
    }
}
