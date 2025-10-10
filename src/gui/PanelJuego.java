package gui;

import javax.swing.JPanel;

import controlador.ControladorJuego;
import modelo.Area;
import modelo.Proyectil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelJuego extends JPanel {
    private VentanaPrincipal ventanaPrincipal;
    private ControladorJuego controladorJuego;
    private ImagenNave imagenNave;
    private Area areaJuego;

    public PanelJuego(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(ventanaPrincipal.getAncho(), ventanaPrincipal.getAlto()));

        areaJuego = new Area(ventanaPrincipal.getAncho(), ventanaPrincipal.getAlto());

        imagenNave = new ImagenNave();

        imagenNave.moverX(areaJuego.getAncho() / 2 - imagenNave.getAncho() / 2, 500);

        this.add(imagenNave);

        controladorJuego = new ControladorJuego(areaJuego);

        imagenNave.setFocusable(true);
        imagenNave.addKeyListener(new KeyAdapter() {
            int nuevoXNave;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 37) { // Flecha izquierda
                    nuevoXNave = controladorJuego.moverNaveIzquierda();
                    imagenNave.moverX(nuevoXNave, imagenNave.getY());
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 39) { // Flecha derecha
                    nuevoXNave = controladorJuego.moverNaveDerecha();
                    imagenNave.moverX(nuevoXNave, imagenNave.getY());
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == 32) { // Espacio
                    Proyectil proyectil = controladorJuego.disparar();
                    // TODO: ControladorProyectiles.agregarProyectil(proyectil);
                }
            }
        });
    }
}
