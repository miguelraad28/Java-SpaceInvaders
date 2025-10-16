package gui;

import controlador.ControladorJuego;
import controlador.ControladorProyectiles;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelo.Area;
import modelo.Dificultad;
import modelo.Proyectil;

public class PanelJuego extends JPanel {
    private Area areaJuego;

    private boolean needsRepaint = false;


    private ImagenNave imagenNave;
    private List<UIProyectil> uiProyectiles;

    private Timer timer;

    public PanelJuego(Dificultad dificultad, Area areaJuego) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(areaJuego.getAncho(), areaJuego.getAlto()));

        this.areaJuego = areaJuego;
        imagenNave = new ImagenNave();
        imagenNave.mover(areaJuego.getAncho() / 2 - imagenNave.getAncho() / 2, 500);
        uiProyectiles = new ArrayList<>();

        this.add(imagenNave);

        imagenNave.setFocusable(true);
        imagenNave.addKeyListener(new KeyAdapter() {
            int nuevoXNave;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 37) { // Flecha izquierda
                    nuevoXNave = ControladorJuego.getInstancia(areaJuego).moverNaveIzquierda();
                    imagenNave.mover(nuevoXNave, imagenNave.getY());
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 39) { // Flecha derecha
                    nuevoXNave = ControladorJuego.getInstancia(areaJuego).moverNaveDerecha();
                    imagenNave.mover(nuevoXNave, imagenNave.getY());
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == 32) { // Espacio
                    Proyectil proyectil = ControladorJuego.getInstancia(areaJuego).disparar();
                    if (proyectil != null) {
                        ControladorProyectiles.getInstancia(areaJuego).agregarProyectil(proyectil);
                        UIProyectil uiProyectil = new UIProyectil(true);
                        System.out.println("Proyectil: " + proyectil.getX() + " " + proyectil.getY());
                        uiProyectil.mover(proyectil.getX(), proyectil.getY());
                        uiProyectiles.add(uiProyectil);
                        add(uiProyectil);
                    }
                }
            }
        });

        iniciarCicloJuego();
    }

    private void iniciarCicloJuego() {
        timer = new Timer(30, e -> {
            System.out.println("Ciclo de juego");
            actualizarCooldownNave();
            actualizarProyectiles();
            actualizarInvasores();
            
            if(needsRepaint) {
                revalidate();
                repaint();
                needsRepaint = false;
            }
        });
        timer.start();
    }

    private void detenerCicloJuego() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    private void actualizarCooldownNave(){
        ControladorJuego.getInstancia(areaJuego).actualizarCooldownNave();
    }

    private void actualizarProyectiles() {
        Map<Integer, Proyectil> proyectilesMap = ControladorProyectiles.getInstancia(areaJuego).moverProyectiles();
        // Iterar hacia atrás para poder eliminar elementos de forma segura
        for (int i = uiProyectiles.size() - 1; i >= 0; i--) {
            UIProyectil uiProyectil = uiProyectiles.get(i);
            System.out.println("Mover UIProyectil: " + uiProyectil.getProyectilID());
            Proyectil proyectil = proyectilesMap.get(uiProyectil.getProyectilID());
            // No se destruyó el proyectil
            if (proyectil != null) {
                uiProyectil.mover(proyectil.getX(), proyectil.getY());
                System.out.println("Mover UIProyectil: " + uiProyectil.getX() + " " + uiProyectil.getY());
            } else {
                // El obj. de negocio ya no existe, lo eliminamos de la UI
                System.out.println("Salió del mapa");
                uiProyectiles.remove(i);
                remove(uiProyectil);
                needsRepaint = true;
            }
        }
    }

    public void actualizarInvasores() {

    }
    
    public void solicitarFocoNave() {
        imagenNave.requestFocusInWindow();
    }
}
