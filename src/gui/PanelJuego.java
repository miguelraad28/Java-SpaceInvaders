package gui;

import javax.swing.JPanel;

import controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelJuego extends JPanel {
    private VentanaPrincipal ventanaPrincipal;
    private Controlador controlador;

    public PanelJuego(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlador = controlador;
        construirPanel();
        construirEventos();
    }

    private void construirPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void construirEventos() {

        ManejoTeclasDeJuego mtj = new ManejoTeclasDeJuego();

        this.addKeyListener(mtj);
    }

    public class ManejoTeclasDeJuego implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case java.awt.event.KeyEvent.VK_LEFT:
                    controlador.moverNaveIzq();
                    break;
                case java.awt.event.KeyEvent.VK_RIGHT:
                    controlador.moverNaveDer();
                    break;
                case java.awt.event.KeyEvent.VK_SPACE:
                    controlador.disparar();
                    break;
                case java.awt.event.KeyEvent.VK_ESCAPE:
                    ventanaPrincipal.mostrarPanelInicial();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}
