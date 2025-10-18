/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import controlador.ControladorJuego;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Area;
import modelo.Dificultad;

/**
 *
 * @author Dell
 */
public class VentanaPrincipal extends JFrame {

    private Container contenedor;
    private PanelJuego panelJuego;
    private Area areaJuego;

    private JTextField txtCargarCreditos;
    private JButton btnIniciarCadete, btnIniciarGuerrero, btnIniciarMaestro, btnVerRanking, btnCargarCreditos;

    private final int ancho = 800;
    private final int alto = 600;

    public VentanaPrincipal() {
        this.setTitle("Space Invaders - POO");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(ancho, alto);
        this.setResizable(false);

        areaJuego = new Area(ancho, alto);

        construirVentana();
        construirEventos();

        this.setVisible(true);
    }

    private void construirVentana() {
        contenedor = this.getContentPane();

        // Crear los botones una sola vez
        btnIniciarCadete = new JButton("Cadete");
        btnIniciarGuerrero = new JButton("Guerrero");
        btnIniciarMaestro = new JButton("Maestro");
        btnVerRanking = new JButton("Ver Ranking");
        btnCargarCreditos = new JButton("Cargar Créditos");
        txtCargarCreditos = new JTextField(10);

        mostrarPanelInicial();
    }

    private void construirEventos() {
        ManejoBotonIniciar mbi = new ManejoBotonIniciar();

        btnIniciarCadete.addActionListener(mbi);
        btnIniciarGuerrero.addActionListener(mbi);
        btnIniciarMaestro.addActionListener(mbi);

        ManejoBotonRanking mbr = new ManejoBotonRanking();

        btnVerRanking.addActionListener(mbr);

        btnCargarCreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorJuego.getInstancia(areaJuego).cargar(Integer.parseInt(txtCargarCreditos.getText()));
                txtCargarCreditos.setText("");
                mostrarPanelInicial();
                mostrarMensaje("Créditos cargados correctamente");
            }
        });

    }

    public void mostrarPanelInicial() {
        // Limpiar el contenedor
        contenedor.removeAll();

        contenedor.setLayout(new GridLayout(6, 1));

        JLabel lblTitulo = new JLabel("Space Invaders", JLabel.CENTER);
        JLabel lblCreditosDisponibles = new JLabel("Créditos disponibles: " + ControladorJuego.getInstancia(areaJuego).obtenerSaldo(),
                JLabel.CENTER);
        JPanel pnlDificultades = new JPanel();

        // Ya no recreamos los botones, solo los reutilizamos
        pnlDificultades.setLayout(new GridLayout(4, 1));

        pnlDificultades.add(new JLabel("Seleccione la dificultad para iniciar:"));
        pnlDificultades.add(btnIniciarCadete);
        pnlDificultades.add(btnIniciarGuerrero);
        pnlDificultades.add(btnIniciarMaestro);

        contenedor.add(lblTitulo);
        contenedor.add(lblCreditosDisponibles);
        contenedor.add(txtCargarCreditos);
        contenedor.add(btnCargarCreditos);
        contenedor.add(pnlDificultades);
        contenedor.add(btnVerRanking);

        // Actualizar la ventana
        contenedor.revalidate();
        contenedor.repaint();
    }

    public void mostrarPanelJuego(Dificultad dificultad) {
        panelJuego = new PanelJuego(dificultad, areaJuego, this);

        this.setContentPane(panelJuego);

        this.revalidate();
        this.repaint();

        panelJuego.solicitarFocoNave();
    }

    public void mostrarPanelRanking() {
        contenedor.removeAll();

        PanelRanking panelRanking = new PanelRanking(this);
        contenedor.setLayout(new BorderLayout());
        contenedor.add(panelRanking, BorderLayout.CENTER);

        // Actualizar la ventana
        contenedor.revalidate();
        contenedor.repaint();
        // Dar foco al panel de juego para capturar las teclas
        panelRanking.requestFocusInWindow();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void iniciarJuego(Dificultad dificultad) {
        if (ControladorJuego.getInstancia(areaJuego).obtenerSaldo() <= 0) {
            mostrarMensaje("No tienes créditos suficientes");
        } else {
            ControladorJuego.getInstancia(areaJuego).iniciarJuego(dificultad);
            mostrarPanelJuego(dificultad);
        }
    }

    public class ManejoBotonIniciar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();

            String dificultad = boton.getText();

            switch (dificultad) {
                case "Cadete":
                    iniciarJuego(Dificultad.CADETE);
                    break;
                case "Guerrero":
                    iniciarJuego(Dificultad.GUERRERO);
                    break;
                case "Maestro":
                    iniciarJuego(Dificultad.MAESTRO);
                    break;
            }
        }
    }

    public class ManejoBotonRanking implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VentanaPrincipal.this.mostrarPanelRanking();
        }
    }
}
